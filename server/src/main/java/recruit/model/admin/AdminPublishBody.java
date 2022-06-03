package recruit.model.admin;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import recruit.model.Recruit;

@Data
@EqualsAndHashCode(callSuper=false)
public class AdminPublishBody extends Recruit {
  private List<String> welfare;
}
