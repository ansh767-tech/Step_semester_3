package Object_Class.assignment_problems;

import java.util.ArrayList;
import java.util.List;

interface NotificationChannel {
    void send(String studentName, String noticeTitle);
}

class EmailChannel implements NotificationChannel {
    @Override
    public void send(String studentName, String noticeTitle) {
        System.out.println("[Email → " + studentName + "] " + noticeTitle);
    }
}

class SmsChannel implements NotificationChannel {
    @Override
    public void send(String studentName, String noticeTitle) {
        System.out.println("[SMS → " + studentName + "] " + noticeTitle);
    }
}

class AppChannel implements NotificationChannel {
    @Override
    public void send(String studentName, String noticeTitle) {
        System.out.println("[App → " + studentName + "] " + noticeTitle);
    }
}

class Student {
    private String name;
    private String department;
    private List<NotificationChannel> preferredChannels = new ArrayList<>();

    public Student(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }

    public void addPreferredChannel(NotificationChannel channel) {
        preferredChannels.add(channel);
    }

    public List<NotificationChannel> getPreferredChannels() {
        return preferredChannels;
    }
}

class Notice {
    private String title;
    private List<String> targetDepartments;

    public Notice(String title, List<String> targetDepartments) {
        this.title = title;
        this.targetDepartments = targetDepartments;
    }

    public String getTitle() { return title; }
    public List<String> getTargetDepartments() { return targetDepartments; }
}

class NoticeBoard {
    private List<Student> registeredStudents = new ArrayList<>();

    public void registerStudent(Student student) {
        registeredStudents.add(student);
    }

    public boolean postNotice(Notice notice) {
        if (notice.getTitle() == null || notice.getTitle().trim().isEmpty()) {
            System.out.println("Cannot post notice: Title is required.");
            return false;
        }

        if (notice.getTargetDepartments() == null || notice.getTargetDepartments().isEmpty()) {
            System.out.println("Cannot post notice: At least one target department is required.");
            return false;
        }

        String deptsString = String.join(", ", notice.getTargetDepartments());
        System.out.println("Notice '" + notice.getTitle() + "' posted to " + deptsString + ".");

        for (Student student : registeredStudents) {
            if (notice.getTargetDepartments().contains(student.getDepartment())) {
                for (NotificationChannel channel : student.getPreferredChannels()) {
                    channel.send(student.getName(), notice.getTitle());
                }
            }
        }
        return true;
    }
}

public class CampusNoticeBroadcaster {
    public static void main(String[] args) {
        NoticeBoard noticeBoard = new NoticeBoard();

        Student asha = new Student("Asha", "CSE");
        asha.addPreferredChannel(new EmailChannel());
        asha.addPreferredChannel(new AppChannel());

        Student ravi = new Student("Ravi", "ECE");
        ravi.addPreferredChannel(new SmsChannel());

        noticeBoard.registerStudent(asha);
        noticeBoard.registerStudent(ravi);

        Notice notice1 = new Notice("Lab Closed Tomorrow", List.of("CSE"));
        noticeBoard.postNotice(notice1);

        Notice notice2 = new Notice("Fee Deadline Extended", List.of("CSE", "ECE"));
        noticeBoard.postNotice(notice2);

        Notice notice3 = new Notice("Sports Day", List.of());
        noticeBoard.postNotice(notice3);
    }
}