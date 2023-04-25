package pl.studentmed.facultative.models.user_info;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.studentmed.facultative.models.BasicEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_infos")
public class UserInfo extends BasicEntity {
}
