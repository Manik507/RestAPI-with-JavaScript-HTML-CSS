CREATE TABLE IF NOT EXISTS students (
  id BIGSERIAL PRIMARY KEY,
  roll_number VARCHAR(50) NOT NULL UNIQUE,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  email VARCHAR(180),
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS courses (
  id BIGSERIAL PRIMARY KEY,
  code VARCHAR(50) NOT NULL UNIQUE,
  name VARCHAR(200) NOT NULL,
  description TEXT,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS enrollments (
  id BIGSERIAL PRIMARY KEY,
  student_id BIGINT NOT NULL REFERENCES students(id) ON DELETE CASCADE,
  course_id BIGINT NOT NULL REFERENCES courses(id) ON DELETE CASCADE,
  UNIQUE (student_id, course_id)
);

CREATE TABLE IF NOT EXISTS attendance_sessions (
  id BIGSERIAL PRIMARY KEY,
  course_id BIGINT NOT NULL REFERENCES courses(id) ON DELETE CASCADE,
  session_date DATE NOT NULL,
  topic VARCHAR(255),
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  UNIQUE (course_id, session_date)
);

CREATE TYPE attendance_status AS ENUM ('PRESENT', 'ABSENT', 'LATE', 'EXCUSED');

CREATE TABLE IF NOT EXISTS attendance_records (
  id BIGSERIAL PRIMARY KEY,
  session_id BIGINT NOT NULL REFERENCES attendance_sessions(id) ON DELETE CASCADE,
  student_id BIGINT NOT NULL REFERENCES students(id) ON DELETE CASCADE,
  status attendance_status NOT NULL,
  marked_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  UNIQUE (session_id, student_id)
);

-- Helpful indexes
CREATE INDEX IF NOT EXISTS idx_students_roll_lower ON students ((LOWER(roll_number)));
CREATE INDEX IF NOT EXISTS idx_students_name_lower ON students ((LOWER(first_name || ' ' || last_name)));
CREATE INDEX IF NOT EXISTS idx_courses_code_lower ON courses ((LOWER(code)));
