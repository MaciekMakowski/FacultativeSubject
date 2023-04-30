package pl.studentmed.facultative.models.user_info;

import lombok.Builder;

public record UserInfoResponseDTO(Long userInfoId,
                                  String firstName,
                                  String lastName,
                                  String email,
                                  String birthdate,
                                  String userRole,
                                  String createdAt,
                                  String modifiedAt
                                  ) implements IUserInfoDTO {

    @Builder public UserInfoResponseDTO {}
}
