package Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import Model.Variant;
import Repository.VariantData;
public interface VariantData extends JpaRepository<Variant, Long> {
    Optional<Variant> findByVariantID(Long stockID);
    Optional<Variant> findByVariantColour(String stockColour);
    Optional<Variant> findByVariantSize(String stockSize);
    Optional<Variant> deleteByVariantID(Long stockID);
}

