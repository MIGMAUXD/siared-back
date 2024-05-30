package BackendSiadseUfps.siadse.dto;

import java.time.Month;

public class MonthlyEnrollment {
    private Month month;
    private Long count;

    public MonthlyEnrollment(Month month, Long count) {
        this.month = month;
        this.count = count;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}