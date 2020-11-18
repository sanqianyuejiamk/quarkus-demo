package org.mengka.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiafeng
 * @version farabbit2.0, 2020/6/8
 * @since farabbit2.0
 */
@Schema( description="Schema da resposta do método buscarTodos.")
public class CfopsResposta {

    public List<Cfops> cfops;

    @Schema(description="Versão do schema de resposta.")
    public String version;

    public CfopsResposta() {
        this.cfops = new ArrayList<>();
        this.version = "3.0.0";
    }
}
