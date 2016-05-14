/**
 * JavaScript con funcionalidades correspondiestes a la presentacion de clientes
 */

$(document)
  .ready(
    function() {
      $("#birthday").datepicker({
        changeMonth: true,
        changeYear: true,
        yearRange: "-100:+0",
        dateFormat: 'dd/mm/yy'
      });

      dataTable = $('#clientsTable')
        .DataTable({
          'processing': false,
          'serverSide': false,
          'sAjaxSource': '/client/list',
          'bJQueryUI': true,
          'autoWidth': true,
          'order': [
            [1, "asc"]
          ],
          'aoColumns': [{
            'mData': 'id'
          }, {
            'mData': 'enabledUser'
          }, {
            'mData': 'name'
          }, {
            'mData': 'userName'
          }, {
            'mData': 'email'
          }, {
            'mData': 'phone',
            "defaultContent": ""
          }],
          'columnDefs': [{
            'targets': 0,
            'searchable': false,
            'ordenable': false,
            'className': 'dt-body-center',
            'render': function(data,
              type, row) {
              return '<input name="col" type="radio" id=' + row.id + ' value=' + row.id + ' >';
            }
          }, {
            'targets': 1,
            'width': '10%',
            'searchable': false,
            'className': 'dt-body-center',
            'render': function(data,
              type, row) {
              return (data === true) ? '<span class="glyphicon glyphicon-ok centerSpan"></span>' : '<span class="glyphicon glyphicon-remove centerSpan"></span>';
            }
          }, {
            "render": function(data,
              type, row) {
              return row.surname + ', ' + data;
            },
            "targets": 2
          }],
          'language': {
            "lengthMenu": "_MENU_ elementos por p&aacute;gina",
            "zeroRecords": "No se obtuvieron resultados",
            "info": "P&aacute;gina _PAGE_ de _PAGES_",
            "infoEmpty": "No se obtuvieron resultados",
            "infoFiltered": "(de _MAX_ elementos)"
          }
        });

      $('#page-wrapper').on(
        'click',
        '#clientsTable tr',
        function() {
          $(this).find('input:radio').attr('checked',
            true);
        });

      $('#clientForm').submit(function(e) {
        var frm = $('#clientForm');
        e.preventDefault();
        $.ajax({
          type: frm.attr('method'),
          url: frm.attr('action'),
          data: frm.serialize(),
          success: function() {
            dataTable.ajax.reload();
            toogle('myContent');
            toogleButtons();
            clearForm(frm[0]);
          },
          error: function(response) {
            $('#passwordsNoMatchRegister').show();
            alert(response.statusText);
          }
        });
      });

      $('#btnAdd').click(function(e) {
        toogle('myContent');
        toogleButtons();
        var frm = $('#clientForm');
        frm.attr("method", "POST");
        $("#userName").removeAttr('disabled');
        $("#pass").show();
        $("#passInput").attr('required','required');
      });

      $('#btnEdit')
        .click(function(e) {
          var id = $('input[type="radio"]:checked').val();
          var frm = $('#clientForm');
          frm.attr("method", "PUT");
          $("#userName").attr('disabled','disabled');
          $("#pass").hide();
          $("#passInput").removeAttr('required');
          toogleButtons();
          if (typeof id === "undefined") {
            toogleButtons();
            alert("Por favor, seleccione un elemento de la lista");
          } else {
            $.ajax({
                type: "GET",
                url: "client/load/" + id,
                success: function(callback) {
                  frm.loadJSON(callback);
                  $('#birthday').val(formatDate($('#birthday').val()));
                  toogle('myContent');
                },
                error: function() {
                  alert("Error!");
                }
              });
          };
        });

      $('#btnDelete')
        .click(
          function() {
            var id = $('input[type="radio"]:checked').val();
            if (typeof id === "undefined") {
              alert("Por favor, seleccione un elemento de la lista");
            } else {
              $.ajax({
                type: "POST",
                url: "/client/disable/" + id,
                success: function() {
                  dataTable.ajax.reload();
                },
                error: function() {
                  alert("Error!");
                }
              });
            };
          });
    });
