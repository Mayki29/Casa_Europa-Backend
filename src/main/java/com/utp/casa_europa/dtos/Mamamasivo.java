package com.utp.casa_europa.dtos;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Mamamasivo {
    private List<Long> productoIds;
    private List<Long> categoriaIds;

    
}
