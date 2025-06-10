CREATE TABLE users (
    user_id VARCHAR(36) PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL
);

CREATE TABLE members (
    member_id SERIAL PRIMARY KEY,
    user_id VARCHAR(36) NOT NULL,
    club_id VARCHAR(36) NOT NULL,
    role VARCHAR(50) NOT NULL DEFAULT 'Member',
    
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (club_id) REFERENCES clubs(club_id) ON DELETE CASCADE,
    
    UNIQUE (user_id, club_id) -- Prevents duplicate members
);

CREATE TABLE announcements (
    announcement_id SERIAL PRIMARY KEY,
    club_id VARCHAR(36) NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    title TEXT NOT NULL,
    body TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (club_id) REFERENCES clubs(club_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE tasks (
    task_id VARCHAR(36) PRIMARY KEY,
    club_id VARCHAR(36) NOT NULL,
    title TEXT NOT NULL,
    deadline DATE NOT NULL,
    status TEXT DEFAULT 'Not Started',
    notes TEXT,

    FOREIGN KEY (club_id) REFERENCES clubs(club_id) ON DELETE CASCADE
);

CREATE TABLE task_assignees (
    task_id VARCHAR(36),
    user_id VARCHAR(36),

    PRIMARY KEY (task_id, user_id),
    FOREIGN KEY (task_id) REFERENCES tasks(task_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);


CREATE TABLE meetings (
    meeting_id VARCHAR(36) PRIMARY KEY,
    club_id VARCHAR(36) NOT NULL,
    title TEXT NOT NULL,
    date DATE NOT NULL,
    time TIME,
    location TEXT,
    description TEXT,
    recurrence TEXT DEFAULT 'NONE',  -- DAILY, WEEKLY, MONTHLY, etc.

    FOREIGN KEY (club_id) REFERENCES clubs(club_id) ON DELETE CASCADE
);


CREATE TABLE events (
    event_id VARCHAR(36) PRIMARY KEY,
    club_id VARCHAR(36) NOT NULL,
    title TEXT NOT NULL,
    date DATE NOT NULL,
    time TIME,
    location TEXT,
    description TEXT,

    FOREIGN KEY (club_id) REFERENCES clubs(club_id) ON DELETE CASCADE
);


