$(document).ready(function () {

    $('#dtcadastroid').val(dataParaInput())

    if ($('#codigoid').val() != "" || $('#codigoid').val() != undefined) {
        encontraProdutoAlterar($('#codigoid').val());
    }

    // Função do submit no formulario de cadastro de produto
    $('#formCadastroProduto').submit(function (event) {
        // cancela o submit normal do form
        event.preventDefault();

        let produto = constroiJsonProduto($('#formCadastroProduto'));

        salvaProduto(produto);

    })

    $('#formProdutoAlterar').submit(function (event) {
        // cancela o submit normal do form
        event.preventDefault();

        let produto = constroiJsonProduto($('#formProdutoAlterar'));

        alteraProduto(produto);

    })

    $('#alterarprodutoid').on('click', function () {
        redirecionarPaginaAlterar($('#codigoid').val());
    })

});

function dataParaInput(algumValor) {

    let dt;

    if (algumValor != undefined) {
        dt = new Date(algumValor);
    } else {
        dt = new Date();
    }

    let month = ("0" + (dt.getMonth() + 1)).slice(-2);
    let day = ("0" + dt.getDate()).slice(-2);

    let today = dt.getFullYear() + "-" + (month) + "-" + (day);

    return today;
}

function constroiElementoProduto(produto) {

    let template = $('#produtoTemplate').clone();

    template.attr('style', 'display: visible');
    template.find('.produto-codigo').text(produto.codigo)
    template.find('.produto-nome').text(produto.nome)
    template.find('.produto-descricao').text(produto.descricao)
    template.find('.produto-peso').text(produto.peso)
    template.find('.produto-preco').text(produto.preco)
    template.find('.produto-dtcadastro').text(produto.dtCadastro)

    template.find('.produto-emestoque').text(produto.emEstoque)
    template.find('.produto-desativado').text(produto.desativado)

    return template;
}

function constroiElementoProdutoForm(produto) {

    let template = $('#formProdutoAlterar');

    template.attr('style', 'display: visible');
    template.find('.produto-codigo').val(produto.codigo)
    template.find('.produto-nome').val(produto.nome)
    template.find('.produto-descricao').val(produto.descricao)
    template.find('.produto-peso').val(produto.peso)
    template.find('.produto-preco').val(produto.preco)
    template.find('.produto-dtcadastro').val(dataParaInput(produto.dtCadastro))

    if (produto.emEstoque) {
        template.find('.produto-emestoque').prop('checked', true)
        template.find('.produto-indisponivel').prop('checked', false)
    } else {
        template.find('.produto-emestoque').prop('checked', false)
        template.find('.produto-indisponivel').prop('checked', true)
    }

    if (produto.desativado) {
        template.find('.produto-desativado').prop('checked', true)
        template.find('.produto-ativado').prop('checked', false)
    } else {
        template.find('.produto-desativado').prop('checked', false)
        template.find('.produto-ativado').prop('checked', true)
    }

    if ($('#detalheid').text() != '') {
        $.each($('.produtoNomeView'), function (indexInArray, valueOfElement) {
            valueOfElement.innerText = produto.nome;
        });
    }

}

function constroiJsonProduto(formProduto) {

    let produto = {
        id: 0,
        codigo: "0",
        nome: formProduto.find('#nomeid').val(),
        descricao: formProduto.find('#descricaoid').val(),
        peso: formProduto.find('#pesoid').val(),
        preco: formProduto.find('#precoid').val(),
        dtCadastro: formProduto.find('#dtcadastroid').val(),
        emEstoque: "",
        desativado: ""
    }

    if ($('#codigoid').val() != "" || $('#codigoid').val() != undefined) {
        produto.codigo = $('#codigoid').val();
    }

    let estoque = formProduto.find('#estoqueid :input:checked').val();
    let status = formProduto.find('#statusid :input:checked').val();

    if (estoque == 1) {
        produto.emEstoque = true;
    } else {
        produto.emEstoque = false;
    }
    if (status == 1) {
        produto.desativado = true;
    } else {
        produto.desativado = false;
    }

    return produto;

}

function salvaProduto(produto) {

    let getUrl = window.location.origin + "/restproduto/save";

    $.ajax({
        type: 'post',
        url: getUrl,
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(produto),
        success: function (response) {
            console.log(response);
        },
        complete: function (response) {
            location.href = window.location.origin + "/produto/" + response.responseText;
        }
    })

}

function alteraProduto(produto) {

    let getUrl = window.location.origin + "/restproduto/update";

    $.ajax({
        type: 'post',
        url: getUrl,
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(produto),
        success: function (response) {
            console.log(response);
            redirecionarPagina(produto.codigo);
            location.href = window.location.origin + "/produto/" + produto.codigo;
        },
        complete: function () {
            location.href = window.location.origin + "/produto/" + produto.codigo;
        }
    })
}

function encontraProduto(codigo) {

    let getUrl = window.location.origin + "/restproduto/code/" + codigo;

    $.ajax({
        type: "get",
        url: getUrl,
        dataType: "json",
        success: function (response) {
            console.log(response);
            let prodElement = constroiElementoProduto(response);
            prodElement.appendTo('#produtoVisivel');
        }
    });
}

function encontraProdutoAlterar(codigo) {

    let getUrl = window.location.origin + "/restproduto/code/" + codigo;

    $.ajax({
        type: "get",
        url: getUrl,
        dataType: "json",
        success: function (response) {
            console.log(response);
            constroiElementoProdutoForm(response);
        }
    });
}

function redirecionarPagina(codigo) {
    window.location.replace(window.location.origin + "/produto/" + codigo)
}

function redirecionarPaginaAlterar(codigo) {
    window.location.replace(window.location.origin + "/produto/alterar/" + codigo)
}