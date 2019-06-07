package com.waal.uteis;

import com.waal.beans.ItensPed;
import com.waal.beans.Pedido;
import com.waal.beans.Pessoa;
import java.util.ArrayList;
import java.util.List;

/**
 * @Autor Winder Rezende
 * @Data 05/06/2019
 */
public class Mensagem {

    public static String html(Pessoa pessoa, Pedido pedido) {

        String htmlMsg = "<!DOCTYPE html><html lang=\"pt-BR\"><head><meta charset=\"UTF-8\" /></head><body><div style=\"width: 560px; margin: auto;\"><center><img src=\"data:image/png;base64, " + base64img + "\" alt=\"\"/>"
                + "<b><h1>Olá " + pessoa.getNome() + "!</h1></b><br>"
                + "<h3>Seu pedido número " + pedido.getId() + " foi realizado na loja Waal Service no dia " + Formatar.data(pedido.getDataEmissao(), "dd/MM/yyyy") + " com sucesso!</h3></center>"
                + "<div style=\"padding: 10px;\"><b>Detalhes:</b><br>"
                + "<p>Status: " + pedido.getStatus()
                + "<table cellspacing=\"0\" border=\"0\">\n"
                + "	<colgroup width=\"80\"></colgroup>\n"
                + "	<colgroup width=\"188\"></colgroup>\n"
                + "	<colgroup width=\"85\"></colgroup>\n"
                + "	<tr>\n"
                + "		<td style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: 1px solid #000000\" height=\"21\" align=\"center\" bgcolor=\"#B9F4E0\">\n"
                + "			<font color=\"#000000\">Item</font>\n"
                + "		</td>\n"
                + "		<td style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: 1px solid #000000\" align=\"center\" bgcolor=\"#B9F4E0\">\n"
                + "			<font color=\"#000000\">Descrição</font>\n"
                + "		</td>\n"
                + "		<td style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: 1px solid #000000\" align=\"center\" bgcolor=\"#B9F4E0\">\n"
                + "			<font color=\"#000000\">Preço</font>\n"
                + "		</td>\n"
                + "	</tr>\n"
                + setRows(pedido)
                + "</table>"
                + "<br>Total em produtos: " + Formatar.formatarNumero(pedido.getTotalProduto())
                + "<br>Total em serviços: " + Formatar.formatarNumero(pedido.getTotalServico())
                + "<br>Descontos: " + Formatar.formatarNumero(pedido.getDesconto())
                + "<br>Total geral: " + Formatar.formatarNumero(pedido.getTotalGeral())
                + "</p></div></div></body></html>";
        //System.out.println(htmlMsg);
        return htmlMsg;
    }

    public static String setRows(Pedido pedido) {

        StringBuilder row = new StringBuilder();

        List<ItensPed> listaItensPed = new ArrayList<>();
        listaItensPed = pedido.getItesPed();

        System.out.println(listaItensPed.size());

        for (ItensPed itensPed : listaItensPed) {
            row.append(
                    "	<tr>\n"
                    + "		<td style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: 1px solid #000000\" height=\"21\" align=\"center\">\n"
                    + "			<font color=\"#000000\">" + (itensPed.getProduto() != null ? "Produto" : "Serviço") + "</font>\n"
                    + "		</td>\n"
                    + "		<td style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: 1px solid #000000\" align=\"left\">\n"
                    + "			<font color=\"#000000\">" + (itensPed.getProduto() != null ? itensPed.getProduto().getNome() : itensPed.getServico().getNome()) + "</font>\n"
                    + "		</td>\n"
                    + "		<td style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: 1px solid #000000\" align=\"center\" sdval=\"25\" sdnum=\"1046;0;&quot;R$&quot; #.##0,00;[RED]-&quot;R$&quot; #.##0,00\">\n"
                    + "			<font color=\"#000000\">" + (itensPed.getProduto() != null ? Formatar.formatarNumero(itensPed.getProduto().getPreco()) : Formatar.formatarNumero(itensPed.getServico().getValor())) + "</font>\n"
                    + "		</td>\n"
                    + "	</tr>\n"
            );
        }
        return row.toString();
    }

    public static String base64img
            = "iVBORw0KGgoAAAANSUhEUgAAAIAAAABaCAYAAABwm16CAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7"
            + "DAcdvqGQAABeJSURBVHhe7Z0JfBVFmsCrr3cleUkIAQIBBOTyAhRUWHYGGK8VL1zZ0VFAdGVGxVlcHWX8rTIzPw8UL0T9DYsoHsPoL"
            + "qIMA14rIHiOoqKIGrlBkSt5ybvf6+7a76uqzjuSQALd/SLw1053V3VXV9f31VdfVdcryDGObiSxd40vfgzDM2UiSVSEWMhib2GKvUw"
            + "opex6j8dD+pd78m88xmHguAK88dUONWx4zjUpuZCa0hCQZFcILuGx2eTLNT9rIl4iNZDWJyox3qvw00Vj+ndN8ohjHAqOKcC73+xQd"
            + "qXVCwzqnWNSqbsJAsSH0ayaTptYgQPDL+dZpsQgikmjKknce0qvLvcNKFOPWYZDwBEFWPr51soEDcxOyp4rqCSB4NCco+Ats25xOI+"
            + "nkJpGFMmA9FOrKjz6uHMHVoVE5DFaie0K8MpHmyp0rXhhSvaeQxtTxwPnKigqmWIm6oMqPeGCk7r8IIJtY9Rv53SrrY8ODofDlUTmP"
            + "knboIRCu1VcXKJ7SXLLp8//4T0RUXBsVYB3N+9Xfmww5iaI91oqm8JkW9iuawJsWkySljTio6mPK7T0iLNPqNJF5CEzbPIfO8j+sok"
            + "/1KZvhPfpkdapB/wXiMl3Vg8Of3MoDCwPsFh+WTdlIzYnYIRm17zy0BYWXSDa/jYHYFcofkVS0q7FN5ZZLcFXtzankKBcFaLA8wwiD"
            + "wsn6U0i4pAZds2skT8kij+q2as8EjECx+uGAsLXIEaBDYss+70OvqHfwzYJ9x4So365gVT8R0jtuvnka+6/Dy4qGJhDW/jr+zVlcqB"
            + "0TVrynsRVHbEt+VYjEaO+nDRUnT+oV1wEtYlh18+5ect+epdO1TIUF9gxEWM/CjWITlRSROqX/LBo+iUi2FVsswCyx3eGniN898GmA"
            + "GpaaUOSThNBbeLMm568enutMgvcShA+CAdqrHNIRJdVEL9O4nLJxT0vn/nZt+Go6zXGtjeUVM9o6O7BUbaJdBc0s1hjw6T03itnv7n"
            + "hipkv/3rtHhPt9kEZc/MT1Vv3JWelTOxdqiB+FZqx/F6LnXCfwJAUlu96vWjwpNvm3iUiXcM+Faf0FCJhgRXOAuC4Apptr+YjIaN0Y"
            + "K3W/c8P/GXFZ1PnLO0vLmmRHXuT8+KGtyN2VbHVxs1pJebPAMB/kUERtoW0/+o3/o7jeKA72KYApmR2gh6/OCsMkuh3Ungrn6aSNNT"
            + "kvUbZyTv1sg+vfeiVkSyyGXqPnzGuNimfJ4EgwACIUHeBZock0rKqBip+J4JcwT4LIClQ/wpj+rnVsTYJXgpqsaSDKHGTSUQPlIEiL"
            + "Lvh8WUD8ep8SsrLfm5KXjhCFRa10mXwuRSswP6I/isR5Ar2KUBBBJ8PCo+bcD0ZZ9127BxK0PdOqOXB3Un/Ux/Xon3IpTacHtn2wR0"
            + "nMMEx9JadPuX+E0WA49ioAIXEUj5LiCZRVRmUIMbaVopjOODQRUnpiHmL3hkvLmrEoN5qcVhwsAkyleDJ4tRxjhAFQLj5t5Ch+uupB"
            + "DGNFJwZxIQ+N7qIDTH5Tn4FZ+hVfyiDpquDOC04OKgVaghXiFPHOYIUIAOqQjqtsyZA1/moMJp4ClYgTPx9bnv69R4sEGiIJUnaMHG"
            + "Yr12APZlEKuVafo4ABeDtfi6WKwd7M8V8AAm0AYeJTJ369tTFRrBogIKzyNNoDz4M5gJ8mMxXNMf5iSmAJezsjYu6JbDWZ4PdLZ0q3"
            + "cXpUY99CsC86PZRizJKAXs8FDuEqYxWXMTPBFbkUYhtCqBQA/pXBhzZVZpCcjnbwcm1ntY9ENh4OzQDktpeNLXg2KYAhqyUtFZIrQN"
            + "lZG2WAtiW/jEFENiiADXhFPNb7BQ/T81q51sDvgpu+bLl52wuB+7xPHemylGNLQrQr8RDZWpGCv0toCmYn6bCbm+5zMfNUUnbmgATk"
            + "jKtamY7mO6B0m4pLlOQmTJt77Xf3a8RtikAq/3MAzuQoFoDvr615XOgtFtXbC2l3G5gZWibWA6KnU8qULk2oxQ46GNidjJxGR+g2Tv"
            + "aGe4VpW0KIBOa5iV7uJnHRPLTOJjYMvHcElGSTqdhj2HNpdWOER/V3cK+JoCa7/IfgBwKKCRra46DxWeQ4ZJ4zCSJlPgGwP7+lAAFc"
            + "LGXYpsCKNRYeeiaawnX2vJpKTwfHEknZMe+ergaZ/bAPSJTlhNI2X+58Etak77T8Dy42ZuyTQHKvdIbiqHv5MV5OC/QWkFYz7Geha8"
            + "isbHIDZu2gPXHc/wVsjXFq/k8VQQ6Ek2RD/uHJPYhkfKS4qg4cRzbFGD0CdWGh8TvbX0nBq9ry0BPS2Seh/3nXXsjpK4B5wBAOJhSR"
            + "UYFgDol5I+1K1sVLh07PKkQo64lBXEXzANYKCNu+8/bWsI2BUCCAXmeZib+gUaWK0JzhYrh1tZWML0WNkgumaJk7ZebiYlmAMAnyCo"
            + "oAGtTQfCwQ79QMjON7G2X/yzu8Xg/yf9qWCg0M0XqI5GvxKnj2KoAZw2o1j0kcrGHJL/D+e6NwskBy76x/A+BpmmisqV0k3zw1Sayq"
            + "z5CqAzx8AhJVoks87kV6AOwp8IBj83gJfXLcAZRocFMeVRt7bbFd2/lIc5jqwIglww5/seSdMNIvx79KLc5yCnzQ6AFAYHgktCC/2P"
            + "DVlKzfS9RQPHYhwkI11QPyBs6qNndQSZo6LJmEYns+UuxF+eOFU4J0GrKkK0yb2KBCHIF2xUAOX9o3z29OvpGeEniToWkWe2T2GRcU"
            + "cDMAnNvPKMWeIRmGhw3ikKEvXXOsonqhPfjrF/uOxiSSb7fHyZ/X7mWfLtlN7tKpgZBCy+B8GXVy+7AtLFrxdIz4d50bAMLFmx/5dG"
            + "Qx6h7VsY+JLvBZUWAvOHk1SI5tZHGd78gQl3BEQVAhvYoNy8b1Pnujkq02E8jN9ZHU8TEX2lh0wACZrNf4To01ygawwSxgqZgZcVz9"
            + "hsfuI7vVdjjMaiTKZP6WJp8t6OeLH/nS/L66nVkfxQnfKIC4fcISF/xwP9+3hOA9HA6GAN2fjmhF6vJz3hAhgkX/tO0oDf5jWywXPF"
            + "Al1DgcbIZD/UsVydt+J+HXF3kwjVV/9eZr36d9FYN8KBTBo5OKpkQHjoUN9RabINNqJ2SpOVkiomCWQxQCtjpRpokUmkCTT7LPf4Ih"
            + "MdB7QaBS1DrZa+f3cjO8QDjYG/Cud9sWPnADaN+MbCYJZrDLXOX9l60atMH9Ya3ExoDTJnnADe7iwrrHs+7R0o0dPIlbln/3O+fYlE"
            + "u4pgFyKdbmf/utE5JQ5KQSFolSeolDXHweKNxEo6lSCgSh5qdJKFYlNRlbXgegmtC0RhpiMVIDBp8/BEqN+koGv6DUKJoRPUEiObxw"
            + "ZkMCgWvhhYF5Q/XsS+VVCcdvKn/bk74yEO/vnBz/yptWFAObYSGRIQidgsf82TgZHUSUBL7qwPJyYUQPuKaAtx41TkLS0joPcX6ASk"
            + "ITPOXEM1XRGTNRwjUXDTdVG5ugyYANlPBDXrtYDlYOK7bAKbe4yuGrQj6UAEoUmxiUPBcxqxJAXlDC0M6eWJL+3b2L2ERLfDmwzduv"
            + "/bCUwZVeuN3+QiOxzSrK4cJJV6Fpsrl+FuSER766YLpi0WE69iv2gfg9gVv9/l2L10bp8WlqHo4h4CXLzPUPDNWe51DthB4PAqYVWo"
            + "R1djON8JTxFAd/pYo4d09ihKj5kw57xsW3QrufOa1TotXbZgY09VLU6Z5mkFVSJBblUZExnleKNHTuiYrsiFjVxRadryEvx28r0ljX"
            + "o+0s0SlH2kkNn/dgjveZxEFJL/UHOeqB/73lBCpWK1LgVKslayLhuba9qzgaBD6ALh2UN2O6pLIpCd/c+FKHndo3PTows7FxaVsGYT"
            + "G3AplwE6OoZuSYVCp5ruNvrIOZXqXThVp8HPAz0UfRSHJeFh+5Kbxu/kd7QO7S71V3Dx3effvY96/hVK+wVQB0y9qEffkcd/2bPE7M"
            + "AVxNySlgnNZokXf69Ox6PKZE4bvZJccI4eCKIDFFTNf/vekFrw2bmpnGiTAhIeeepNMiVrGEJGWG4e1kakMOoboVkFt89I0dK0Sqzt"
            + "6jdmlQWPFI5PPP7Z+YAsUVAEs7npxdd9Nu6LDI0nzOM3jL4H2U5LZwBGu9yHMQ0swTTCJCfaXSno6GNA20Hjd58/cfNkhj6ePuvJ3n"
            + "aJJc0hDONZb83qC8HRo0aFjyJQO/8CWnyUWhwrJDtBTwLOkX5W3Tbnq0reuu3RUA0a0N9qFArQHnl3yjueex18c4Sspm7EvHB+WNpU"
            + "ik6hQQpYLl01usVln/DomeHbExx7A46cJ4pPoCilZ+/yW1QtdHeo9GMcUABg6dmp5xPA/VKsbkw22HiA6behLgCB5G9P6kmKyx4uFO"
            + "mCTRnHYmhI/BPtJdK1fCV/21evPufbB50Ac9QrQbcyEwV5vx6UNKU81DijhABOTOcgPrTmXf3YxccFyso8t8FqeACoQDlSxULwU/0C"
            + "UZkRCnfz66C+Wzf2cRRaQ7DdzhTOnzu6glnceATVtdDgS+3nSILgEK5azyEtzhSqABhZ/10epqUCP3JCUQDKVTn1f6pc29KtMf/DSr"
            + "b9aKq5sFWeMv7V6Z21kTVwqPeDKXChKnjkrb1axHSCvAq4AVgrQNcUmxaDQNQ2HBlQXn7Hy+Vk17MICYb2J44y+fX55Wvb9plYPTA2"
            + "lta44YoeFw8wsbC0VJWYwOw41hd+H4KBvmp17zBTxq/onJ3eU73/59+MWsegDsOjtz7QZjy9ctDumXoQjO9S0FrRvBjDhLJ5NGsmoA"
            + "89Yy0rAYnCMA11IGfwJdieG4vtqpIiEPlgy/76Rp/YMZo87u0oLb2wvEx9b2vuLnamX9+q+wTr7EsiLgoN7MTycRX7GMNYKs0TAw3g"
            + "odv+w3S6CWtatqP6xeXdePu1ELzO8zTLsl7des3mPPp+qHkgIaynPlQUz3yB0w4BaiwrAnsbhqXJ1ydzRFH4HFznWfElWiIKTVBSZf"
            + "c9QQXn7dJKueX/hrGfYpQXgQPm3hbNmvDB0U52yPKZWVlKaO/cSv7ghOCJoHyb7EHS8t/b9mVPHjRrZzZ8z+cOi79hpb9SmfeegkJs"
            + "rBtNIw2ZwPwBFKC5hO9MgMiiO5mnbSi7WUxRVA0uG6wJSUkwb9u14a06liHIdW4s+n3H3Lu719b7kO7VmRXecxMFn6lrWLvvRQhMOE"
            + "17X2JOIbMqku3f3y588MPEyHpvhllkLuj/3+qfbTYkPPvE8cWcNMQwdxxVYDpkCwB+std26dSP9+vcjvY/vTSorO7IwkCOzPM3SGAw"
            + "5wwvZEQ+WZHgeHPywo4b07141/fQ+FQ+e1j0oZjO6R7YUbGf0n15d8HWddxLUFywCEeo8rIDBxHqlNDmhpO68N/404Q0ewznp3Osu3"
            + "mUGXzVx+bhGhURAHUDwzOxn5be4OEBGnT2GDBo8CNpy+96EWxbUIJN4jMT+YsW4p7OXPH7RmX2atVpOkFF7m5ky7+3TttWZV2JLySd"
            + "tuAdqNa5WkgZ/Y1vE83RNgnfmLBRfUTWuypkvSvxXPbjwOViz+/XvS6ZOu4kMOXUw1Fp71Rj9F7QwWEJJJVgRosGHt8WUdU+v+qaPu"
            + "MRxHFOAjzbuvyxGA+j6QqE5amiaBb8poNmt131d/zhv+RgRzPAE/KXMoRdwp5IS0wQfRZhzFH6v3j3J2IvGEsWrOaLCWCp8k6BPpHO"
            + "/QC4eGCO+Nc+s/gr/dTXHcUwB0lSG2o//Wa/pPihK9PK37gpdwEM4KHxslLj5F/mD2o8OIc8pOGclATL+in8jgWCxqKX2w/LHNtYYA"
            + "Ly3EZX9VRE98PfVm/c7vl6gIwpw798+7hJJSOD44cvxv4UC5xvEDU+TlcLzHTcTtII7fHACcWPO/gXx+KCLiDgh/WawlAAzkZCLhmz"
            + "YUXs7C3IQRxTg8w07eqQklZlh/LeDsGALBT46RtUqfsYBU8v6I5Zc2TLxuJSsyGgPMP2DBkObD1e5JHsGexY0A0wHYR+lvul/XfOFD"
            + "4OdwhEFMFDo8AJsxA7nALpZis1g6HpAHDYP5A8tAjP1sPXr25flv7BQkiJqUdjw5TRfduOUAkD5cY/Zat0Kh4QzhZM18dyeQAZspCC"
            + "PVjYh3wMGDmgX+VZlhcSI93wR4AiOKAD64OKAUeii1NNppZ//QA2R5eODsmgeEigKoB4UGBOaJVROuXFdYydwSAHaGbxZzSFbG7Kj/"
            + "X4f8Xq9TZxE98FcQb5wwMJBHFKA9qRXYMzzZGmiawpV3Or0IdgMoDMoy+C3yFD0BTcBKH6ZGOwXLs5xdFgALM0sTKB5ARe61mdwywc"
            + "5WhQgB6jgSmbQx73Cbo8cnQoAb81HArngsTkovNOXj6WWzmbsqFSA/AqPRV14py8fdzTyiFcALEaZyMo32V8EQdo5c3zwAPrc7Kdqj"
            + "aHuCKAl3Hr6Ea8AXJw4oYMdZIAStgqZrw+EAdAoMEuA543qURDcevpR0ATgsDQ1+2mZgSBq6mkUshWAQrdWCWsvq4W5xZGvACBl6PX"
            + "lVKh0vG4L9AQaFQDru8KsAGWDQPhlMOeGI5gjXwHQsjMRZ6juXLlGpUlxxsFxAbQRuq6zwaBCgznOzbUzOPOmkk5xsSac9YtfBAsJz"
            + "g1UFCUhThmvzb9vt08za8DeQyHjCCAEyhoesXNcq6jQvQK3nu6IdDpVlO+CUhRniBu63DxsFTKqfy9OG+kSVGbhz8kNyBrz/qHW46K"
            + "SuK4gwr5mMt+gsIrgNI4ogGbEa0vkZILVLhFWKLB2KzS1S5w28uFLjzxV4dPX83UFABC0ovCfo7PJn1mCb+nYcfijHH2gIwrwxPWXR"
            + "IoUfQPWLPzAUkhUqOVVQd8acZpDn6rAVQESjVO4ihUEjhFrKpsdnI9lDdz8SEQl9tlKnDmDIwqAdPGnnvCQFBQ/FpjzStCSYAIkEi0"
            + "i9S+J0xxee2rmuqqgcoE3HQLhckuATiBzCMWGuCn0DI3PdLRf6pgC3DJl/HNBKboVXwObAqfJmGb+RDTmuCRduV978dUZk1tcH+iTx"
            + "bNXlHsiQzp4U/tkUIIUKABfIxAbA17js2u+e00AOKPwHmCM6kSAIzimAGdX+/TO/tQ0jSThJRx7TBOwycGPOzJRiM8Ib+/XtWiGiGq"
            + "RDW89//nG5Q9XBqXam/00vqt2bx1TAasOWkpg4ZYSsPkJpr5enDqCo5JZdc+EJb38dXP9Mv4DDu7AhAbyCUjhhsGdtSkvTD2nSQ+gJ"
            + "Tb+37xHty5/pGuQhh5jP1wFi2CBSmBtiBtKoBKdeGVzlTh1BMer5uPTJ91Qre27XzVw1U2cJo6hWIiZwm0NeF9Ld2CcQoXDBBdVSNF"
            + "Q70Di+lfuuDTnN4GtpWdV5T0aTYP88aMxjgpmnmwJ3ikFwF4LbtgcKcTcWV6iLRdRjuC4Agwulcz37588fXhX47pyWhthC0NDgeb+K"
            + "PPQQf+C/YfCMpOkAwl9PLxP4Kw3775yobikzUwY2WePRmNPY7r5WFYARwsdtQJUxx7Ms5cPrd4vQhyhbdXwMFm5Pabd9vALN0Tl4PU"
            + "RXetvKDjunp+FplnKnr2XKXLuWiqgUH5Fjwek2JpuQfm5E3pWLHvw6nMPe13AxR9v0nbGtc0J01ONS9xni9oxpxaSZWmDYgXk9LIyO"
            + "TLpyn8eeOQoQDa3PvN6xbpNu0+nqlZMTGGIsFzZckH5JdxES7CMiOLxpOsaYjs6+OmWh2+fWHtiERvUtY0/r/rypKhcuiJFSyolkvn"
            + "FtqNmE95ApYn1HX3S2InDu28XoY5RMAX4qTD/ve+GRlPqSwk50FsEAQ4VG/gxPhL/IOiRfnn1iON2iFBHOaYArWD1pj3+ddvr707J6"
            + "n/q1AcGS0UHTdgpUYStLklhpLJsFXf6jJBPTj1oxGqf/O2/nOpo3z+bYwrQBpas39l1Z21qXMpUx1GinAlBRdxRtLaDg+4vXBoHR9I"
            + "PZ9+rsrlCNtLr/ApZeM3Pejf5ZuE0xxTgEPnwx6i8/tutZZrKewOt9T7Qd8GvzR5Vlaip02H9u4cGdC5p5d12Q8j/A5mcPLEwYJf0A"
            + "AAAAElFTkSuQmCC";
}
