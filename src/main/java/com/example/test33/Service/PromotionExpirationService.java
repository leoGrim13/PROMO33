package com.example.test33.Service;

import com.example.test33.Model.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Component
public class PromotionExpirationService {

    @Autowired
    private PromotionService promotionService;

    @Scheduled(cron = "0 27 1 * * *")
    public void removeExpiredPromotions() {
        Date currentDate = new Date();
        List<Promotion> promotions = promotionService.getAllPromotions();

        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(currentDate);

        for (Promotion promotion : promotions) {
            Date expirationDate = promotion.getFinpromo();

            Calendar expirationCalendar = Calendar.getInstance();
            expirationCalendar.setTime(expirationDate);

            if (currentCalendar.after(expirationCalendar)) {
                promotionService.deletePromotion(promotion.getId());
            }
        }
    }



}

