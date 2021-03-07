import { Marca } from "./marca";
import { TipoProduto } from "./tipo-produto";

export class Produto {

    id_produto: number;
    descricao: String;
    tipo_produto: TipoProduto;
    marca: Marca;
}
