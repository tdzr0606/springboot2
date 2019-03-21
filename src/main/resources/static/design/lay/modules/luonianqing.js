//form.js
/*Form.prototype.loadData = function (data, filter) {
    $.each(data, function (key, value) {
        var inputType = $('#' + filter + ' [name="' + key + '"]').attr('type');
        if ("checkbox" == inputType) {
            $('#' + filter + ' [name="' + key + '"]').prop("checked", value);
        }
        else {
            $('#' + filter + ' [name="' + key + '"]').val(value);
        }
    });
};*/

//table.js
/*that.elem.on('click', 'td', function()
    {
        var othis = $(this);
        var fieldIndex= othis.attr("data-field");
        if(fieldIndex!=0) // 如果不是第checkBox点击
        {
            var childs = that.layBody.find('input[name="layTableCheckbox"]');
            var index = othis.parents('tr').eq(0).data('index');
            childs.each(function(i, item){
              if(item.id=="tableCheckBox"+index)
              {
                  item.checked = true;
                  that.setCheckData(i, true);
              }
              else
              {
                  item.checked = false;
                  that.setCheckData(i, false);
              }
            });
            // console.log(table.cache[that.key][index]);
            that.syncCheckAll();
            that.renderForm();
            layui.event.call(this, MOD_NAME, 'checkbox('+ filter +')', {
                checked: true
                ,data: table.cache[that.key][index]
                ,type: 'one'
            });
        }

    });*/