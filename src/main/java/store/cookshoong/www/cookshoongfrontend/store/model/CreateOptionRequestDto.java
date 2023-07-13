package store.cookshoong.www.cookshoongfrontend.store.model;

import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 옵션 등록 Dto.
 *
 * @author papel
 * @since 2023.07.11
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateOptionRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String price;
}
