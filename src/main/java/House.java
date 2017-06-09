import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class House {
    HouseType houseType;
    int number;
}
