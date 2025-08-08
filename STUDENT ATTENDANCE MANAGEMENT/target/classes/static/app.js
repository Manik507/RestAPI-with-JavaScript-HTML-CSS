const api = {
  students: '/api/students',
  courses: '/api/courses',
  attendance: '/api/attendance',
};

const toast = document.getElementById('toast');
function showToast(msg){ toast.textContent = msg; toast.classList.add('show'); setTimeout(()=>toast.classList.remove('show'), 1800); }
async function req(url, opts = {}) {
  const res = await fetch(url, { headers: { 'Content-Type': 'application/json' }, ...opts });
  if (!res.ok) {
    let message = 'Error ' + res.status;
    try {
      const data = await res.json();
      if (data.message) message = data.message;
    } catch {}
    throw new Error(message);
  }
  if (res.status === 204) return null;
  return res.json();
}

/* Tabs */
document.querySelectorAll('.tab').forEach(btn=>{
  btn.addEventListener('click', ()=>{
    document.querySelectorAll('.tab').forEach(b=>b.classList.remove('active'));
    document.querySelectorAll('.panel').forEach(p=>p.classList.remove('active'));
    btn.classList.add('active');
    document.getElementById('tab-'+btn.dataset.tab).classList.add('active');
  });
});

/* Students */
const sEls = {
  form: document.getElementById('student-form'),
  id: document.getElementById('student-id'),
  roll: document.getElementById('rollNumber'),
  first: document.getElementById('firstName'),
  last: document.getElementById('lastName'),
  email: document.getElementById('email'),
  reset: document.getElementById('student-reset'),
  msg: document.getElementById('student-msg'),
  search: document.getElementById('student-search'),
  tbody: document.getElementById('students-tbody'),
};
let students = [];

function renderStudents() {
  const q = (sEls.search.value || '').toLowerCase().trim();
  const filtered = students.filter(s => (`${s.firstName} ${s.lastName}`).toLowerCase().includes(q));
  sEls.tbody.innerHTML = filtered.map(s => `
    <tr>
      <td>${esc(s.rollNumber)}</td>
      <td>${esc(s.firstName)} ${esc(s.lastName)}</td>
      <td class="hide-sm">${esc(s.email || '')}</td>
      <td>
        <button class="btn" data-edit-stu="${s.id}">Edit</button>
        <button class="btn" data-del-stu="${s.id}">Delete</button>
      </td>
    </tr>
  `).join('');
  sEls.tbody.querySelectorAll('[data-edit-stu]').forEach(b=>{
    b.addEventListener('click', ()=>{
      const id = Number(b.getAttribute('data-edit-stu'));
      const st = students.find(x=>x.id===id);
      sEls.id.value = st.id; sEls.roll.value = st.rollNumber; sEls.first.value = st.firstName; sEls.last.value = st.lastName; sEls.email.value = st.email || '';
      sEls.msg.textContent = 'Editing student #' + st.id;
    });
  });
  sEls.tbody.querySelectorAll('[data-del-stu]').forEach(b=>{
    b.addEventListener('click', async ()=>{
      const id = Number(b.getAttribute('data-del-stu'));
      if (!confirm('Delete this student?')) return;
      await req(`${api.students}/${id}`, { method: 'DELETE' });
      students = students.filter(x=>x.id!==id);
      renderStudents(); refreshEnrollStudents(); showToast('Deleted');
    });
  });
}

sEls.form.addEventListener('submit', async (e)=>{
  e.preventDefault();
  const payload = {
    rollNumber: sEls.roll.value.trim(),
    firstName: sEls.first.value.trim(),
    lastName: sEls.last.value.trim(),
    email: sEls.email.value.trim() || null,
  };
  if (!payload.rollNumber || !payload.firstName || !payload.lastName) {
    sEls.msg.textContent = 'Please fill required fields.'; return;
  }
  if (sEls.id.value) {
    const data = await req(`${api.students}/${Number(sEls.id.value)}`, { method: 'PUT', body: JSON.stringify(payload) });
    students = students.map(x=>x.id===data.id?data:x);
    showToast('Student updated');
  } else {
    const data = await req(api.students, { method: 'POST', body: JSON.stringify(payload) });
    students.unshift(data);
    showToast('Student created');
  }
  sEls.form.reset(); sEls.id.value=''; sEls.msg.textContent='';
  renderStudents(); refreshEnrollStudents();
});
sEls.reset.addEventListener('click', ()=>{ sEls.form.reset(); sEls.id.value=''; sEls.msg.textContent=''; });
sEls.search.addEventListener('input', renderStudents);

async function loadStudents(){
  students = await req(api.students);
  renderStudents(); refreshEnrollStudents();
}

/* Courses */
const cEls = {
  form: document.getElementById('course-form'),
  id: document.getElementById('course-id'),
  code: document.getElementById('code'),
  name: document.getElementById('name'),
  desc: document.getElementById('description'),
  reset: document.getElementById('course-reset'),
  msg: document.getElementById('course-msg'),
  tbody: document.getElementById('courses-tbody'),
  select: document.getElementById('course-select'),
  refresh: document.getElementById('refresh-course'),

  enrollCourse: document.getElementById('enroll-course'),
  enrollStudent: document.getElementById('enroll-student'),
  enrollBtn: document.getElementById('enroll-btn'),
  enrolledList: document.getElementById('enrolled-list'),
};
let courses = [];

function renderCourses() {
  cEls.tbody.innerHTML = courses.map(c=>`
    <tr>
      <td>${esc(c.code)}</td>
      <td>${esc(c.name)}</td>
      <td class="hide-sm">${esc(c.description || '')}</td>
      <td>
        <button class="btn" data-edit-course="${c.id}">Edit</button>
        <button class="btn" data-del-course="${c.id}">Delete</button>
      </td>
    </tr>
  `).join('');
  cEls.select.innerHTML = courses.map(c=>`<option value="${c.id}">${esc(c.code)} - ${esc(c.name)}</option>`).join('');
  cEls.enrollCourse.innerHTML = cEls.select.innerHTML;
  cEls.enrollStudent.innerHTML = students.map(s=>`<option value="${s.id}">${esc(s.rollNumber)} - ${esc(s.firstName)} ${esc(s.lastName)}</option>`).join('');
  attCourseSelect.innerHTML = cEls.select.innerHTML;

  // bind row actions
  cEls.tbody.querySelectorAll('[data-edit-course]').forEach(b=>{
    b.addEventListener('click', ()=>{
      const id = Number(b.getAttribute('data-edit-course'));
      const cc = courses.find(x=>x.id===id);
      cEls.id.value = cc.id; cEls.code.value = cc.code; cEls.name.value = cc.name; cEls.desc.value = cc.description || '';
      cEls.msg.textContent = 'Editing course #' + cc.id;
    });
  });
  cEls.tbody.querySelectorAll('[data-del-course]').forEach(b=>{
    b.addEventListener('click', async ()=>{
      const id = Number(b.getAttribute('data-del-course'));
      if (!confirm('Delete this course?')) return;
      await req(`${api.courses}/${id}`, { method: 'DELETE' });
      courses = courses.filter(x=>x.id!==id);
      renderCourses(); showToast('Deleted');
    });
  });

  refreshEnrolled();
}

cEls.form.addEventListener('submit', async (e)=>{
  e.preventDefault();
  const payload = {
    code: cEls.code.value.trim(),
    name: cEls.name.value.trim(),
    description: cEls.desc.value.trim() || null,
  };
  if (!payload.code || !payload.name) { cEls.msg.textContent = 'Please fill required fields.'; return; }
  if (cEls.id.value) {
    const data = await req(`${api.courses}/${Number(cEls.id.value)}`, { method: 'PUT', body: JSON.stringify(payload) });
    courses = courses.map(x=>x.id===data.id?data:x); showToast('Course updated');
  } else {
    const data = await req(api.courses, { method: 'POST', body: JSON.stringify(payload) });
    courses.unshift(data); showToast('Course created');
  }
  cEls.form.reset(); cEls.id.value=''; cEls.msg.textContent=''; renderCourses();
});
cEls.reset.addEventListener('click', ()=>{ cEls.form.reset(); cEls.id.value=''; cEls.msg.textContent=''; });
cEls.refresh.addEventListener('click', async ()=>{ await loadCourses(); });

cEls.enrollBtn.addEventListener('click', async ()=>{
  const courseId = Number(cEls.enrollCourse.value);
  const studentId = Number(cEls.enrollStudent.value);
  await req(`${api.courses}/${courseId}/enroll/${studentId}`, { method: 'POST' });
  showToast('Enrolled'); refreshEnrolled();
});

async function refreshEnrolled(){
  if (!cEls.enrollCourse.value) return;
  const courseId = Number(cEls.enrollCourse.value);
  const enrolled = await req(`${api.courses}/${courseId}/students`);
  cEls.enrolledList.innerHTML = enrolled.map(s=>`
    <li class="pill">${esc(s.rollNumber)} · ${esc(s.firstName)} ${esc(s.lastName)}
      <span class="x" data-unenroll="${courseId}:${s.id}">×</span>
    </li>`).join('');
  cEls.enrolledList.querySelectorAll('[data-unenroll]').forEach(el=>{
    el.addEventListener('click', async ()=>{
      const [cId, sId] = el.getAttribute('data-unenroll').split(':').map(Number);
      await req(`${api.courses}/${cId}/enroll/${sId}`, { method: 'DELETE' });
      refreshEnrolled(); showToast('Unenrolled');
    });
  });
}

function refreshEnrollStudents(){ // when students list changes
  cEls.enrollStudent.innerHTML = students.map(s=>`<option value="${s.id}">${esc(s.rollNumber)} - ${esc(s.firstName)} ${esc(s.lastName)}</option>`).join('');
}

async function loadCourses(){
  courses = await req(api.courses);
  renderCourses();
}

/* Attendance */
const attCourseSelect = document.getElementById('att-course');
const attDate = document.getElementById('att-date');
const attTopic = document.getElementById('att-topic');
const createSessionBtn = document.getElementById('create-session');
const sessionMsg = document.getElementById('session-msg');
const sessionInfo = document.getElementById('session-info');
const refreshRosterBtn = document.getElementById('refresh-roster');
const rosterTbody = document.getElementById('roster-tbody');

const sumPresent = document.getElementById('sum-present');
const sumAbsent = document.getElementById('sum-absent');
const sumLate = document.getElementById('sum-late');
const sumExcused = document.getElementById('sum-excused');

let currentSession = null;

createSessionBtn.addEventListener('click', async ()=>{
  const courseId = Number(attCourseSelect.value);
  const date = attDate.value;
  const topic = attTopic.value.trim() || null;
  if (!courseId || !date) { sessionMsg.textContent = 'Select course and date.'; return; }
  const session = await req(`${api.attendance}/sessions`, { method: 'POST', body: JSON.stringify({ courseId, date, topic }) });
  currentSession = session;
  sessionMsg.textContent = 'Session ready (ID ' + session.id + ')';
  sessionInfo.textContent = `Course #${session.courseId} • ${session.date} • ${session.topic || 'No topic'}`;
  await loadRoster();
});

refreshRosterBtn.addEventListener('click', async () => { await loadRoster(); });

async function loadRoster(){
  if (!currentSession) return;
  const rows = await req(`${api.attendance}/sessions/${currentSession.id}/roster`);
  rosterTbody.innerHTML = rows.map(r => `
    <tr>
      <td>${esc(r.rollNumber)}</td>
      <td>${esc(r.name)}</td>
      <td>
        <select data-stu="${r.studentId}">
          ${opt('PRESENT', r.status)}${opt('ABSENT', r.status)}${opt('LATE', r.status)}${opt('EXCUSED', r.status)}
        </select>
      </td>
    </tr>
  `).join('');
  rosterTbody.querySelectorAll('select[data-stu]').forEach(sel=>{
    sel.addEventListener('change', async ()=>{
      const studentId = Number(sel.getAttribute('data-stu'));
      const status = sel.value;
      try {
        await req(`${api.attendance}/sessions/${currentSession.id}/attendance`, { method: 'POST', body: JSON.stringify({ studentId, status }) });
        showToast('Saved');
        await refreshSummary();
      } catch (e) {
        showToast(e.message);
      }
    });
  });
  await refreshSummary();
}

function opt(val, current){
  const sel = current === val ? ' selected' : '';
  return `<option value="${val}"${sel}>${val}</option>`;
}
async function refreshSummary(){
  if (!currentSession) return;
  const s = await req(`${api.attendance}/sessions/${currentSession.id}/summary`);
  sumPresent.textContent = `Present: ${s.present}`;
  sumAbsent.textContent = `Absent: ${s.absent}`;
  sumLate.textContent = `Late: ${s.late}`;
  sumExcused.textContent = `Excused: ${s.excused}`;
}

/* Utils */
function esc(s){ return String(s ?? '').replace(/[&<>"']/g, m => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;','\'':'&#39;'}[m])); }

/* Init */
document.addEventListener('DOMContentLoaded', async ()=>{
  await loadStudents(); await loadCourses();
  // Preselect first course if any
  if (courses[0]) {
    attCourseSelect.value = String(courses[0].id);
    cEls.enrollCourse.value = String(courses[0].id);
    await refreshEnrolled();
  }
  // Default date = today
  const today = new Date().toISOString().slice(0,10);
  document.getElementById('att-date').value = today;
});
