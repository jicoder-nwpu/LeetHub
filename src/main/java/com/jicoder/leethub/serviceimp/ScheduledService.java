package com.jicoder.leethub.serviceimp;

import com.jicoder.leethub.pojo.Problem;
import com.jicoder.leethub.pojo.User;
import com.jicoder.leethub.service.ProToUserService;
import com.jicoder.leethub.service.ProblemService;
import com.jicoder.leethub.service.UserService;
import com.jicoder.leethub.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduledService {

    @Resource
    private TemplateEngine templateEngine;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProToUserService proToUserService;

    @Autowired
    private ProblemService problemService;

    @Scheduled(cron = "0 0 8 * * *")
    public void summaryInfo() throws MessagingException {
        List<User> users = userService.getAllUser();
        for(User user : users){
            summaryProblem(user);
        }
    }

    @Async
    public void summaryProblem(User user) throws MessagingException {
        long timestamp = Utils.getStartOfToday();
        Timestamp start_time = new Timestamp(timestamp - 24 * 60 * 60 * 1000);
        Timestamp end_time = new Timestamp(timestamp);
        List<Problem> problems = proToUserService.getByDay(user.getUser_id(), start_time, end_time);
        List<Problem> pws = problemService.getSolutionProblems(problems, user);
        List<Problem> pnws = new ArrayList<>();
        if(pws.size() < problems.size()){
            pnws = problemService.getNoSolutionProblems(problems, user);
        }
        if(pws.size() <= 0){
            pws = null;
        }
        if(pnws.size() <= 0){
            pnws = null;
        }

        Context context = new Context();
        context.setVariable("total",problems.size());
        context.setVariable("pws", pws);
        context.setVariable("pnws", pnws);

        String emailContent = templateEngine.process("email/summary", context);
        emailService.sendHtmlMail(user.getEmail(),"LeetCode刷题日报",emailContent);
    }

}
