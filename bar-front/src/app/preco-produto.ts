import { Produto } from "./produto"

export class PrecoProduto {

    id_preco: number;
    produto: Produto;
    porcentagem_lucro?: number;
    preco_custo: number;
    preco_venda?: number;
}
