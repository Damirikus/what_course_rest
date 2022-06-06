package com.example.what_course.service;

import com.example.what_course.rest.CourseClient;
import com.example.what_course.rest.GifClient;
import com.example.what_course.model.dto.CourseDto;
import com.example.what_course.model.dto.CurrencyDto;
import com.example.what_course.model.dto.GifDto;
import com.example.what_course.model.dto.ImageDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Random;

@Service
//@PropertySource("classpath:application.properties")
public class CourseServiceImpl implements CourseService{

    @Value("${gif.api.id}")
    private String gifApiId;

    @Value("${course.app.id}")
    private String courseAppId;

    @Value("${path.course}")
    private String currency;

    private final static String RICH = "rich";
    private final static String BROKE = "broke";
    private final static int ONE = 1;
    private final static int ZERO = 0;
    private final static int RANDOM_OFFSET = 5000;
    private final static String DATA_PATTERN = "yyyy-MM-dd";
    private final static String JSON_EXPANSE = ".json";
    private final static String ORIGINAL = "original";

    private final CourseClient client;
    private final GifClient gifClient;

    public CourseServiceImpl(CourseClient client, GifClient gifClient) {
        this.client = client;
        this.gifClient = gifClient;
    }

    @Override
    public CurrencyDto getCurrency() {
        CurrencyDto currencyDto = new CurrencyDto();
        currencyDto.setCurrency(currency);
        return currencyDto;
    }

    private CourseDto getLatestCourse(){
        return client.getCourse(courseAppId);
    }

    private CourseDto getYesterdayCourse(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATA_PATTERN);
        String text = dtf.format(LocalDateTime.now().minusDays(ONE));
        return client.getHistCourse(courseAppId, text + JSON_EXPANSE);
    }

    @Override
    public ImageDto getGif(String currency) {

        try {
            Map<String, Double> ratesToday = getLatestCourse().getRates();
            Map<String, Double> ratesYesterday = getYesterdayCourse().getRates();

            String upperCurrency = currency.toUpperCase();

            if (!ratesToday.containsKey(upperCurrency)) {
                return null;
            }

            double today = ratesToday.get(upperCurrency);
            double yesterday = ratesYesterday.get(upperCurrency);

            Random random = new Random();

            ImageDto imageDto;
            GifDto gifDto;

            if (today > yesterday) {
                gifDto = gifClient.getGif(gifApiId, RICH, ONE, random.nextInt(RANDOM_OFFSET));
            } else {
                gifDto = gifClient.getGif(gifApiId, BROKE, ONE, random.nextInt(RANDOM_OFFSET));
            }
            imageDto = gifDto.getData().get(ZERO).getImages().get(ORIGINAL);
            imageDto.setTitle(gifDto.getData().get(ZERO).getTitle());
            imageDto.setToday(String.valueOf(today));
            imageDto.setYesterday(String.valueOf(yesterday));

            return imageDto;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
