package desaweb.tareaback.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoVigente {
    VIGENTE("VIGENTE"),
    NOVIGENTE("NO VIGENTE");

    private final String descripcion;
}
