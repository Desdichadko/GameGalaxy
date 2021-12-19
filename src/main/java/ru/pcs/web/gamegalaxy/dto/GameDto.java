package ru.pcs.web.gamegalaxy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameDto {
    private Long gameId;
    private String name;
    private String price;
    private String posterFileName;
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


    @Nullable
    public BigDecimal getPrice() {
        return StringUtils.isBlank(price) ? null : new BigDecimal(price);
    }

    @Nullable
    public String getDescription() {
        return getStringValueOrNull(description);
    }

    /**
     * Packs platforms boolean into one String with separator "|"
     * @return String platforms or null
     */
    @Nullable
    public String getPlatforms() {
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
        for (int i = 0; i < listOfPlatforms.size() - 1; i++) {
            result.append(listOfPlatforms.get(i)).append("|");
        }
        if (listOfPlatforms.size() > 0) {
            result.append(listOfPlatforms.get(listOfPlatforms.size() - 1));
        }
        return StringUtils.isBlank(result) ? null : result.toString();
    }

    @Nullable
    public String getYoutubeLink() {
        return getStringValueOrNull(youtubeLink);
    }

    public Boolean getIsIndie() {
        return isIndie != null ? isIndie : false;
    }

    @Nullable
    public Double getMcScore() {
        return getDoubleValueOrNull(mcScore);
    }

    @Nullable
    public Double getUserScore() {
        return getDoubleValueOrNull(userScore);
    }

    @Nullable
    public Double getOverallScore() {
        if (getUserScore() != null && getMcScore() != null) {
            return (getMcScore() + getUserScore()) / 2;
        } else if (getMcScore() != null) {
            return getMcScore();
        } else if (getUserScore() != null) {
            return getUserScore();
        }
        return null;
    }

    @Nullable
    public String getDeveloper() {
        return getStringValueOrNull(developer);
    }

    @Nullable
    public String getPublisher() {
        return getStringValueOrNull(publisher);
    }

    @Nullable
    public String getSetting() {
        return getStringValueOrNull(setting);
    }

    @Nullable
    public String getMainGenre() {
        return getStringValueOrNull(mainGenre);
    }

    @Nullable
    public String getSideGenre1() {
        return getStringValueOrNull(sideGenre1);
    }

    @Nullable
    public String getSideGenre2() {
        return getStringValueOrNull(sideGenre2);
    }

    @Nullable
    public String getProcessor() {
        return getStringValueOrNull(processor);
    }

    @Nullable
    public String getGraphicsCard() {
        return getStringValueOrNull(graphicsCard);
    }

    @Nullable
    public Integer getRam() {
        return StringUtils.isBlank(ram) ? null : Integer.parseInt(ram);
    }

    public @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate getReleaseDate() {
        return releaseDate;
    }

    @Nullable
    public Double getFreeMemory() {
        return getDoubleValueOrNull(freeMemory);
    }


    /**
     * Tries to get some information from String or returns null
     */
    @Nullable
    private String getStringValueOrNull(String string) {
        return StringUtils.isBlank(string) ? null : string;
    }

    /**
     * Tries to parse Double from String or returns null
     * @param string String to be parsed
     * @return Double or Null
     */
    @Nullable
    private Double getDoubleValueOrNull(String string) {
        return StringUtils.isBlank(string) ? null : Double.parseDouble(string);
    }
}
