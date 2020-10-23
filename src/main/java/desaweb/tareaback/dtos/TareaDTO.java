package desaweb.tareaback.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TareaDTO {
    @NotEmpty(message = "La descripcion no puede estar vacia")
    private String descripcion;
    private String fechaCreacion;
    private String vigente;
}
