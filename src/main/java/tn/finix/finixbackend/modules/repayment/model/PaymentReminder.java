package tn.finix.finixbackend.modules.repayment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tn.finix.finixbackend.modules.user.model.User;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentReminder {

    private Long id;

    private RepaymentSchedule schedule;

    private User user;

    private ReminderType reminderType;

    private ReminderChannel channel;

    private LocalDateTime sentAt;

    private boolean delivered;

    private boolean resultedInPayment;
}


