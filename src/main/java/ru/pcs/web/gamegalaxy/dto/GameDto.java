package ru.pcs.web.gamegalaxy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameDto {
    private Long id;
    private String name;
    private String price;
    private File poster;
    private String description;
    private String platformPS4;
    private String platformPS5;
    private String platformPC;
    private String youtubeLink;
    private String mcScore;
    private String userScore;
    private String overallScore;
    private String developer;
    private String publisher;
    private LocalDate releaseDate;
    private String setting;
    private String mainGenre;
    private String sideGenre1;
    private String sideGenre2;
    private Boolean isIndie;
    private String processor;
    private String graphicsCard;
    private String ram;
    private String freeMemory;


    public BigDecimal getPrice() {
        return StringUtils.isBlank(price) ? null : new BigDecimal(price);
    }

    public String getDescription() {
        return getStringValueOrNull(description);
    }

    // TODO: realise converter https://www.baeldung.com/jpa-attribute-converters
    public String getPlatforms(){
        StringBuilder result = new StringBuilder();
        List<String> listOfPlatforms = new ArrayList<>();
        if (getStringValueOrNull(platformPS4) != null) {
            listOfPlatforms.add(platformPS4);
        }
        if (getStringValueOrNull(platformPS5) != null) {
            listOfPlatforms.add(platformPS5);
        }
        if (getStringValueOrNull(platformPC) != null) {
            listOfPlatforms.add(platformPC);
        }
        for (int i = 0; i < listOfPlatforms.size()-1; i++) {
            result.append(listOfPlatforms.get(i)).append("|");
        }
        if (listOfPlatforms.size()>0) {
            result.append(listOfPlatforms.get(listOfPlatforms.size()-1));
        }
        return StringUtils.isBlank(result) ? null : result.toString();
    }

    public String getYoutubeLink(){
        return getStringValueOrNull(youtubeLink);
    }

    public Boolean getIsIndie(){
        return isIndie != null ? isIndie : false;
    }

    public Double getMcScore() {
        return getDoubleValueOrNull(mcScore);
    }

    public Double getUserScore() {
        return getDoubleValueOrNull(userScore);
    }

    public Double getOverallScore() {
        if (getUserScore() == null) {
            return getMcScore();
        }
        return (getMcScore() + getUserScore())/2;
    }

    public String getDeveloper() {
        return getStringValueOrNull(developer);
    }

    public String getPublisher() {
        return getStringValueOrNull(publisher);
    }

    public String getSetting() {
        return getStringValueOrNull(setting);
    }

    public String getMainGenre() {
        return getStringValueOrNull(mainGenre);
    }

    public String getSideGenre1() {
        return getStringValueOrNull(sideGenre1);
    }

    public String getSideGenre2() {
        return getStringValueOrNull(sideGenre2);
    }

    public String getProcessor() {
        return getStringValueOrNull(processor);
    }

    public String getGraphicsCard() {
        return getStringValueOrNull(graphicsCard);
    }

    public Integer getRam() {
       return StringUtils.isBlank(ram) ? null : Integer.parseInt(ram);
    }

    public @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Double getFreeMemory() {
        return getDoubleValueOrNull(freeMemory);
    }

    public String getPoster() {
            try {
//                File tempFile = new File(poster.getAbsolutePath());
//                if (tempFile.isFile()) {
                    return poster.getCanonicalPath();
//                }
            }
            catch (IOException ignore) {};
//        return oldPoster;
        return null;
    }


    private String getStringValueOrNull(String string) {
        return StringUtils.isBlank(string) ? null : string;
    }

    private Double getDoubleValueOrNull(String string) {
        return StringUtils.isBlank(string) ? null : Double.parseDouble(string);
    }
}
