package store.cookshoong.www.cookshoongfrontend.store.model;

import java.time.LocalTime;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 영업시간 등록 Dto.
 *
 * @author papel
 * @since 2023.07.10
 */
@Getter
@AllArgsConstructor
public class CreateBusinessHourRequestDto {

    @NotBlank
    private String dayCodeName;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime openHour;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime closeHour;
}
