package BackendSiadseUfps.siadse.dto;

import java.util.List;

public class StudentReportDTO {
    private long totalStudents;
    private List<MonthlyEnrollment> monthlyEnrollments;

    public StudentReportDTO(long totalStudents, List<MonthlyEnrollment> monthlyEnrollments) {
        this.totalStudents = totalStudents;
        this.monthlyEnrollments = monthlyEnrollments;
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public List<MonthlyEnrollment> getMonthlyEnrollments() {
        return monthlyEnrollments;
    }

    public void setMonthlyEnrollments(List<MonthlyEnrollment> monthlyEnrollments) {
        this.monthlyEnrollments = monthlyEnrollments;
    }
}