/**

 @Name：layui.form 表单组件
 @Author：贤心
 @License：MIT

 */

layui.define('layer', function (exports)
{
    "use strict";
    var lang = navigator.language || navigator.browserLanguage;
    if (/^zh/.test(lang))  // 中文
    {
        lang = "zh";
    }
    else if (/^en/.test(lang)) // 英文
    {
        lang = "en";
    }
    // else if (/^fr/.test(lang)) //法文
    // {
    //     lang = "fr";
    // }
    else
    {
        lang = "zh";
    }
    var $ = layui.$
        , layer = layui.layer
        , hint = layui.hint()
        , device = layui.device()
        , MOD_NAME = 'form', ELEM = '.layui-form', THIS = 'layui-this', SHOW = 'layui-show', HIDE = 'layui-hide',
        DISABLED = 'layui-disabled'
        , Form = function ()
        {
            this.config = {
                verify: {
                    required: [
                        /[\S]+/
                        , (lang == 'zh') ? '必填项不能为空' : ((lang == 'en') ? 'Mandatory fields cannot be empty' : '')
                    ]
                    , photo: [
                        /[\S]+/
                        , (lang == 'zh') ? '请上传照片' : ((lang == 'en') ? 'Please upload photos' : '')
                    ]
                    , phone: [/^1[3456789]\d{9}$/
                        , (lang == 'zh') ? '请输入正确的手机号' : ((lang == 'en') ? 'Please enter the correct phone number' : '')
                    ]
                    , email: [
                        /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/
                        , (lang == 'zh') ? '邮箱格式不正确' : ((lang == 'en') ? 'Incorrect email format' : '')
                    ]
                    , url: [
                        /(^#)|(^http(s*):\/\/[^\s]+\.[^\s]+)/
                        , (lang == 'zh') ? '链接格式不正确' : ((lang == 'en') ? 'Link format is incorrect' : '')
                    ]
                    , date: [
                        /^(\d{4})[-\/](\d{1}|0\d{1}|1[0-2])([-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/
                        , (lang == 'zh') ? '日期格式不正确' : ((lang == 'en') ? 'Date format is incorrect' : '')
                    ]
                    , identity: [
                        /(^\d{15}$)|(^\d{17}(x|X|\d)$)/
                        , (lang == 'zh') ? '请输入正确的身份证号' : ((lang == 'en') ? 'Please enter the correct id number' : '')
                    ]
                    , unsignNum: [
                        /^[1-9]\d*$/
                        , (lang == 'zh') ? '只能输入正整数' : ((lang == 'en') ? 'Only positive integers can be entered' : '')
                    ]
                    , numberAndletter: [
                        /^([a-zA-Z0-9.]{5,100})$/
                        , (lang == 'zh') ? '只能输入数字、字母和点' : ((lang == 'en') ? 'Only Numbers, letters and dots can be entered' : '')
                    ]
                    , pass: [
                        /^[\S]{6,32}$/
                        , (lang == 'zh') ? '密码必须6到32位，且不能出现空格' : ((lang == 'en') ? 'Passwords must be 6 to 32 bits and no Spaces allowed' : '')
                    ]
                    , uniCode: [
                        /^[\S]{16}$/
                        , (lang == 'zh') ? '授权编号必须是16位，且不能出现空格' : ((lang == 'en') ? 'The authorization number must be 16 digits and no Spaces are allowed' : '')
                    ]
                    , postCode: [
                        /^[1-9][0-9]{5}$/
                        , (lang == 'zh') ? "请输入正确的邮政编码" : ((lang == 'en') ? 'Please enter the correct zip code' : '')
                    ]
                    , money: [
                        /^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$/
                        , (lang == 'zh') ? '格式不正确,例如2.87' : ((lang == 'en') ? 'The format is incorrect, for example 2.87' : '')
                    ]
                    , checkCode: [
                        /^[A-Za-z0-9]{4}$/
                        , (lang == 'zh') ? '你输入的验证码格式不正确' : ((lang == 'en') ? 'The captcha you entered is not formatted correctly' : '')
                    ]
                    , fax: [
                        /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/
                        , (lang == 'zh') ? '你输入的传真号码格式不正确,例如0871-823XXXX' : ((lang == 'en') ? 'The fax number you entered is not in the correct format, for example 0871-823xxxx' : '')
                    ]
                    , tel: [
                        /^((\d2,3)|(\d{3}\-))?(0\d2,3|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/
                        ,
                        (lang == 'zh') ? '你输入的电话号码格式不正确,例如021-823XXXX' : ((lang == 'en') ? 'The phone number you entered is not in the correct format, for example 021-823xxxx' : '')
                    ]
                    , number: function (value)
                    {
                        if (isNaN(value)) return (lang == 'zh') ? '只能填写数字' : ((lang == 'en') ? 'Number only' : '')
                    }
                    , username: function (value)
                    {
                        if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value))
                        {
                            return (lang == 'zh') ? '用户名不能有特殊字符' : ((lang == 'en') ? 'The user name cannot have special characters' : '');
                        }
                        if (/(^\_)|(\__)|(\_+$)/.test(value))
                        {
                            return (lang == 'zh') ? '用户名首尾不能出现下划线\'_\'' : ((lang == 'en') ? 'The end and end of user name cannot be underlined' : '')
                        }
                    }
                    , length2: function (value)
                    {
                        if (value.length > 2)
                        {
                            return (lang == 'zh') ? '您输入的字数超过限制字数2!' : ((lang == 'en') ? 'The number of words you entered exceeds the limit word number 2' : '');
                        }
                    }
                    , length5: function (value)
                    {
                        if (value.length > 5)
                        {
                            return (lang == 'zh') ? '您输入的字数超过限制字数5!' : ((lang == 'en') ? 'The number of words you entered' +
                                                                                         ' exceeds the limit word number 5' : '');
                        }
                    }
                    , length10: function (value)
                    {
                        if (value.length > 10)
                        {
                            return (lang == 'zh') ? '您输入的字数超过限制字数10!' : ((lang == 'en') ? 'The number of words you entered' +
                                                                                          ' exceeds the limit word number 10 ' : '');
                        }
                    }
                    , length20: function (value)
                    {
                        if (value.length > 20)
                        {
                            return (lang == 'zh') ? '您输入的字数超过限制字数20!' : ((lang == 'en') ? 'The number of words you entered exceeds the limit word number 20 ' : '');
                        }
                    }
                    , length22: function (value)
                    {
                        if (value.length > 22)
                        {
                            return (lang == 'zh') ? '您输入的字数超过限制字数22!' : ((lang == 'en') ? 'The number of words you entered exceeds the limit word number 22 ' : '');
                        }
                    }
                    , length25: function (value)
                    {
                        if (value.length > 25)
                        {
                            return (lang == 'zh') ? '您输入的字数超过限制字数25!' : ((lang == 'en') ? 'The number of words you entered exceeds the limit word number 25 ' : '');
                        }
                    }
                    , length100: function (value)
                    {
                        if (value.length > 100)
                        {
                            return (lang == 'zh') ? '您输入的字数超过限制字数100!' : ((lang == 'en') ? 'The number of words you entered exceeds the limit word number' +
                                                                                           ' 100 ' : '');
                        }
                    }
                    , length150: function (value)
                    {
                        if (value.length > 150)
                        {
                            return (lang == 'zh') ? '您输入的字数超过限制字数150!' : ((lang == 'en') ? 'The number of words you entered exceeds the limit word number' +
                                                                                           ' 150' : '');
                        }
                    }
                    , length200: function (value)
                    {
                        if (value.length > 200)
                        {
                            return (lang == 'zh') ? '您输入的字数超过限制字数200!' : ((lang == 'en') ? 'The number of words you entered exceeds the limit word number' +
                                                                                           ' 200' : '');
                        }
                    }
                    , length4000: function (value)
                    {
                        if (value.length > 4000)
                        {
                            return (lang == 'zh') ? '您输入的字数超过限制字数4000!' : ((lang == 'en') ? 'The number of words you entered exceeds the limit word number' +
                                                                                            ' 4000' : '');
                        }
                    }
                    , length10000: function (value)
                    {
                        if (value.length > 10000)
                        {
                            return (lang == 'zh') ? '您输入的字数超过限制字数10000!' : ((lang == 'en') ? 'The number of words you entered exceeds the limit word number' +
                                                                                             ' 10000' : '');
                        }
                    }
                    , decimal: [
                        /^([\s\S]{0,16})$/
                        , (lang == 'zh') ? '最大上限为16' : ((lang == 'en') ? 'Maximum upper limit is 16' : '')
                    ]
                    , accntno: function (value)
                    {
                        if (value.length < 9)
                        {
                            return (lang == 'zh') ? '银行账号长度不能小于9位' : ((lang == 'en') ? 'The length of the bank account cannot be less than 9 digits' : '');
                        }
                    }
                }
            };
        };
    //全局设置
    Form.prototype.set = function (options)
    {
        var that = this;
        $.extend(true, that.config, options);
        return that;
    };
    //全局设置 数据装入
    Form.prototype.loadData = function (data, filter)
    {
        $.each(data, function (key, value)
        {
            var inputType = $('#' + filter + ' [name="' + key + '"]').attr('type');
            if ("checkbox" == inputType)
            {
                $('#' + filter + ' [name="' + key + '"]').prop("checked", value);
            }
            else
            {
                $('#' + filter + ' [name="' + key + '"]').val(value);
            }
        });
    };
    //验证规则设定
    Form.prototype.verify = function (settings)
    {
        var that = this;
        $.extend(true, that.config.verify, settings);
        return that;
    };
    //表单事件监听
    Form.prototype.on = function (events, callback)
    {
        return layui.onevent.call(this, MOD_NAME, events, callback);
    };
    //表单控件渲染
    Form.prototype.render = function (type, filter)
    {
        var that = this
            , elemForm = $(ELEM + function ()
        {
            return filter ? ('[lay-filter="' + filter + '"]') : '';
        }())
            , items = {
            //下拉选择框
            select: function ()
            {
                var TIPS = (lang == 'zh') ? '请选择' : ((lang == 'en') ? 'Please select a ' : ''), CLASS = 'layui-form-select',
                    TITLE = 'layui-select-title'
                    , NONE = 'layui-select-none', initValue = '', thatInput
                    , selects = elemForm.find('select'), hide = function (e, clear)
                    {
                        if (!$(e.target).parent().hasClass(TITLE) || clear)
                        {
                            $('.' + CLASS).removeClass(CLASS + 'ed ' + CLASS + 'up');
                            thatInput && initValue && thatInput.val(initValue);
                        }
                        thatInput = null;
                    }
                    , events = function (reElem, disabled, isSearch)
                    {
                        var select = $(this)
                            , title = reElem.find('.' + TITLE)
                            , input = title.find('input')
                            , dl = reElem.find('dl')
                            , dds = dl.children('dd')
                        if (disabled) return;
                        //展开下拉
                        var showDown = function ()
                        {
                            var top = reElem.offset().top + reElem.outerHeight() + 5 - win.scrollTop()
                                , dlHeight = dl.outerHeight();
                            reElem.addClass(CLASS + 'ed');
                            dds.removeClass(HIDE);
                            //上下定位识别
                            if (top + dlHeight > win.height() && top >= dlHeight)
                            {
                                reElem.addClass(CLASS + 'up');
                            }
                        }, hideDown = function (choose)
                        {
                            reElem.removeClass(CLASS + 'ed ' + CLASS + 'up');
                            input.blur();
                            if (choose) return;
                            notOption(input.val(), function (none)
                            {
                                if (none)
                                {
                                    initValue = dl.find('.' + THIS).html();
                                    input && input.val(initValue);
                                }
                            });
                        };
                        //点击标题区域
                        title.on('click', function (e)
                        {
                            reElem.hasClass(CLASS + 'ed') ? (
                                hideDown()
                            ) : (
                                hide(e, true),
                                    showDown()
                            );
                            dl.find('.' + NONE).remove();
                        });
                        //点击箭头获取焦点
                        title.find('.layui-edge').on('click', function ()
                        {
                            input.focus();
                        });
                        //键盘事件
                        input.on('keyup', function (e)
                        {
                            var keyCode = e.keyCode;
                            //Tab键
                            if (keyCode === 9)
                            {
                                showDown();
                            }
                        }).on('keydown', function (e)
                        {
                            var keyCode = e.keyCode;
                            //Tab键
                            if (keyCode === 9)
                            {
                                hideDown();
                            }
                            else if (keyCode === 13)
                            { //回车键
                                e.preventDefault();
                            }
                        });
                        //检测值是否不属于select项
                        var notOption = function (value, callback, origin)
                        {
                            var num = 0;
                            layui.each(dds, function ()
                            {
                                var othis = $(this)
                                    , text = othis.text()
                                    , not = text.indexOf(value) === -1;
                                if (value === '' || (origin === 'blur') ? value !== text : not) num++;
                                origin === 'keyup' && othis[not ? 'addClass' : 'removeClass'](HIDE);
                            });
                            var none = num === dds.length;
                            return callback(none), none;
                        };
                        //搜索匹配
                        var search = function (e)
                        {
                            var value = this.value, keyCode = e.keyCode;
                            if (keyCode === 9 || keyCode === 13
                                || keyCode === 37 || keyCode === 38
                                || keyCode === 39 || keyCode === 40
                            )
                            {
                                return false;
                            }
                            notOption(value, function (none)
                            {
                                if (none)
                                {
                                    dl.find('.' + NONE)[0] || dl.append('<p class="' + NONE + '">无匹配项</p>');
                                }
                                else
                                {
                                    dl.find('.' + NONE).remove();
                                }
                            }, 'keyup');
                            if (value === '')
                            {
                                dl.find('.' + NONE).remove();
                            }
                        };
                        if (isSearch)
                        {
                            input.on('keyup', search).on('blur', function (e)
                            {
                                thatInput = input;
                                initValue = dl.find('.' + THIS).html();
                                setTimeout(function ()
                                {
                                    notOption(input.val(), function (none)
                                    {
                                        initValue || input.val(''); //none && !initValue
                                    }, 'blur');
                                }, 200);
                            });
                        }
                        //选择
                        dds.on('click', function ()
                        {
                            var othis = $(this), value = othis.attr('lay-value');
                            var filter = select.attr('lay-filter'); //获取过滤器
                            if (othis.hasClass(DISABLED)) return false;
                            if (othis.hasClass('layui-select-tips'))
                            {
                                input.val('');
                            }
                            else
                            {
                                input.val(othis.text());
                                othis.addClass(THIS);
                            }
                            othis.siblings().removeClass(THIS);
                            select.val(value).removeClass('layui-form-danger')
                            layui.event.call(this, MOD_NAME, 'select(' + filter + ')', {
                                elem: select[0]
                                , value: value
                                , othis: reElem
                            });
                            hideDown(true);
                            return false;
                        });
                        reElem.find('dl>dt').on('click', function (e)
                        {
                            return false;
                        });
                        //关闭下拉
                        $(document).off('click', hide).on('click', hide);
                    }
                selects.each(function (index, select)
                {
                    var othis = $(this)
                        , hasRender = othis.next('.' + CLASS)
                        , disabled = this.disabled
                        , value = select.value
                        , selected = $(select.options[select.selectedIndex]) //获取当前选中项
                        , optionsFirst = select.options[0];
                    if (typeof othis.attr('lay-ignore') === 'string') return othis.show();
                    var isSearch = typeof othis.attr('lay-search') === 'string'
                        , placeholder = optionsFirst ? (
                        optionsFirst.value ? TIPS : (optionsFirst.innerHTML || TIPS)
                    ) : TIPS;
                    //替代元素
                    var reElem = $(['<div class="' + (isSearch ? '' : 'layui-unselect ') + CLASS +
                                    (disabled ? ' layui-select-disabled' : '') + '">'
                        , '<div class="' + TITLE + '"><input type="text" placeholder="' + placeholder + '" value="' +
                          (value ? selected.html() : '') + '" ' + (isSearch ? '' : 'readonly') + ' class="layui-input' +
                          (isSearch ? '' : ' layui-unselect') + (disabled ? (' ' + DISABLED) : '') + '">'
                        , '<i class="layui-edge"></i></div>'
                        , '<dl class="layui-anim layui-anim-upbit' + (othis.find('optgroup')[0] ? ' layui-select-group' : '') +
                          '">' + function (options)
                          {
                              var arr = [];
                              layui.each(options, function (index, item)
                              {
                                  if (index === 0 && !item.value)
                                  {
                                      arr.push(
                                          '<dd lay-value="" class="layui-select-tips">' + (item.innerHTML || TIPS) + '</dd>');
                                  }
                                  else if (item.tagName.toLowerCase() === 'optgroup')
                                  {
                                      arr.push('<dt>' + item.label + '</dt>');
                                  }
                                  else
                                  {
                                      arr.push('<dd lay-value="' + item.value + '" class="' + (value === item.value ? THIS : '') +
                                               (item.disabled ? (' ' + DISABLED) : '') + '">' + item.innerHTML + '</dd>');
                                  }
                              });
                              arr.length === 0 && arr.push('<dd lay-value="" class="' + DISABLED + '">没有选项</dd>');
                              return arr.join('');
                          }(othis.find('*')) + '</dl>'
                        , '</div>'].join(''));
                    hasRender[0] && hasRender.remove(); //如果已经渲染，则Rerender
                    othis.after(reElem);
                    events.call(this, reElem, disabled, isSearch);
                });
            }
            //复选框/开关
            , checkbox: function ()
            {
                var CLASS = {
                    checkbox: ['layui-form-checkbox', 'layui-form-checked', 'checkbox']
                    , _switch: ['layui-form-switch', 'layui-form-onswitch', 'switch']
                }
                    , checks = elemForm.find('input[type=checkbox]')
                    , events = function (reElem, RE_CLASS)
                {
                    var check = $(this);
                    //勾选
                    reElem.on('click', function ()
                    {
                        var filter = check.attr('lay-filter') //获取过滤器
                            , text = (check.attr('lay-text') || '').split('|');
                        if (check[0].disabled) return;
                        check[0].checked ? (
                            check[0].checked = false
                                , reElem.removeClass(RE_CLASS[1]).find('em').text(text[1])
                        ) : (
                            check[0].checked = true
                                , reElem.addClass(RE_CLASS[1]).find('em').text(text[0])
                        );
                        layui.event.call(check[0], MOD_NAME, RE_CLASS[2] + '(' + filter + ')', {
                            elem: check[0]
                            , value: check[0].value
                            , othis: reElem
                        });
                    });
                }
                checks.each(function (index, check)
                {
                    var othis = $(this), skin = othis.attr('lay-skin')
                        , text = (othis.attr('lay-text') || '').split('|'), disabled = this.disabled;
                    if (skin === 'switch') skin = '_' + skin;
                    var RE_CLASS = CLASS[skin] || CLASS.checkbox;
                    if (typeof othis.attr('lay-ignore') === 'string') return othis.show();
                    //替代元素
                    var hasRender = othis.next('.' + RE_CLASS[0]);
                    var reElem = $(['<div class="layui-unselect ' + RE_CLASS[0] + (
                        check.checked ? (' ' + RE_CLASS[1]) : '') + (disabled ? ' layui-checkbox-disbaled ' + DISABLED : '') +
                                    '" lay-skin="' + (skin || '') + '">'
                        , {
                              _switch: '<em>' + ((check.checked ? text[0] : text[1]) || '') + '</em><i></i>'
                          }[skin] || ((check.title.replace(/\s/g, '') ? ('<span>' + check.title + '</span>') : '') +
                                      '<i class="layui-icon">' + (skin ? '&#xe605;' : '&#xe618;') + '</i>')
                        , '</div>'].join(''));
                    hasRender[0] && hasRender.remove(); //如果已经渲染，则Rerender
                    othis.after(reElem);
                    events.call(this, reElem, RE_CLASS);
                });
            }
            //单选框
            , radio: function ()
            {
                var CLASS = 'layui-form-radio', ICON = ['&#xe643;', '&#xe63f;']
                    , radios = elemForm.find('input[type=radio]')
                    , events = function (reElem)
                {
                    var radio = $(this), ANIM = 'layui-anim-scaleSpring';
                    reElem.on('click', function ()
                    {
                        var name = radio[0].name, forms = radio.parents(ELEM);
                        var filter = radio.attr('lay-filter'); //获取过滤器
                        var sameRadio = forms.find('input[name=' + name.replace(/(\.|#|\[|\])/g, '\\$1') + ']'); //找到相同name的兄弟
                        if (radio[0].disabled) return;
                        layui.each(sameRadio, function ()
                        {
                            var next = $(this).next('.' + CLASS);
                            this.checked = false;
                            next.removeClass(CLASS + 'ed');
                            next.find('.layui-icon').removeClass(ANIM).html(ICON[1]);
                        });
                        radio[0].checked = true;
                        reElem.addClass(CLASS + 'ed');
                        reElem.find('.layui-icon').addClass(ANIM).html(ICON[0]);
                        layui.event.call(radio[0], MOD_NAME, 'radio(' + filter + ')', {
                            elem: radio[0]
                            , value: radio[0].value
                            , othis: reElem
                        });
                    });
                };
                radios.each(function (index, radio)
                {
                    var othis = $(this), hasRender = othis.next('.' + CLASS), disabled = this.disabled;
                    if (typeof othis.attr('lay-ignore') === 'string') return othis.show();
                    //替代元素
                    var reElem = $(['<div class="layui-unselect ' + CLASS + (radio.checked ? (' ' + CLASS + 'ed') : '') +
                                    (disabled ? ' layui-radio-disbaled ' + DISABLED : '') + '">'
                        , '<i class="layui-anim layui-icon">' + ICON[radio.checked ? 0 : 1] + '</i>'
                        , '<span>' + (radio.title || ((lang == 'zh') ? '未命名' : ((lang == 'en') ? ' unnamed ' : ''))) + '</span>'
                        , '</div>'].join(''));
                    hasRender[0] && hasRender.remove(); //如果已经渲染，则Rerender
                    othis.after(reElem);
                    events.call(this, reElem);
                });
            }
        };
        type ? (
            items[type] ? items[type]() : hint.error( ((lang == 'zh') ? ('不支持的' + type + '表单渲染') : ((lang == 'en') ?  (' unsupported ' + type + ' form rendering')  : '')) )
        ) : layui.each(items, function (index, item)
        {
            item();
        });
        return that;
    };
    //表单提交校验
    var submit = function ()
    {
        var button = $(this), verify = form.config.verify, stop = null
            , DANGER = 'layui-form-danger', field = {}, elem = button.parents(ELEM)
            , verifyElem = elem.find('*[lay-verify]') //获取需要校验的元素
            , formElem = button.parents('form')[0] //获取当前所在的form元素，如果存在的话
            , fieldElem = elem.find('input,select,textarea') //获取所有表单域
            , filter = button.attr('lay-filter'); //获取过滤器
        //开始校验
        layui.each(verifyElem, function (_, item)
        {
            var othis = $(this)
                , vers = othis.attr('lay-verify').split('|')
                , verType = othis.attr('lay-verType') //提示方式
                , value = othis.val();
            othis.removeClass(DANGER);
            layui.each(vers, function (_, thisVer)
            {
                var isTrue //是否命中校验
                    , errorText = '' //错误提示文本
                    , isFn = typeof verify[thisVer] === 'function';
                //匹配验证规则
                if (verify[thisVer])
                {
                    var isTrue = isFn ? errorText = verify[thisVer](value, item) : !verify[thisVer][0].test(value);
                    errorText = errorText || verify[thisVer][1];
                    //如果是必填项或者非空命中校验，则阻止提交，弹出提示
                    if (isTrue)
                    {
                        //提示层风格
                        if (verType === 'tips')
                        {
                            layer.tips(errorText, function ()
                            {
                                if (typeof othis.attr('lay-ignore') !== 'string')
                                {
                                    if (item.tagName.toLowerCase() === 'select' || /^checkbox|radio$/.test(item.type))
                                    {
                                        return othis.next();
                                    }
                                }
                                return othis;
                            }(), {tips: 1});
                        }
                        else if (verType === 'alert')
                        {
                            layer.alert(errorText, {title: ((lang == 'zh') ? '提示' : ((lang == 'en') ? 'Prompt' : '')), shadeClose: true});
                        }
                        else
                        {
                            layer.msg(errorText, {icon: 5, shift: 6});
                        }
                        if (!device.android && !device.ios) item.focus(); //非移动设备自动定位焦点
                        othis.addClass(DANGER);
                        return stop = true;
                    }
                }
            });
            if (stop) return stop;
        });
        if (stop) return false;
        var nameIndex = {}; //数组 name 索引
        layui.each(fieldElem, function (_, item)
        {
            item.name = (item.name || '').replace(/^\s*|\s*&/, '');
            if (!item.name) return;
            //用于支持数组 name
            if (/^.*\[\]$/.test(item.name))
            {
                var key = item.name.match(/^(.*)\[\]$/g)[0];
                nameIndex[key] = nameIndex[key] | 0;
                item.name = item.name.replace(/^(.*)\[\]$/, '$1[' + (nameIndex[key]++) + ']');
            }
            if (/^checkbox|radio$/.test(item.type) && !item.checked) return;
            field[item.name] = item.value;
        });
        //获取字段
        return layui.event.call(this, MOD_NAME, 'submit(' + filter + ')', {
            elem: this
            , form: formElem
            , field: field
        });
    };
    //自动完成渲染
    var form = new Form()
        , dom = $(document), win = $(window);
    form.render();
    //表单reset重置渲染
    dom.on('reset', ELEM, function ()
    {
        var filter = $(this).attr('lay-filter');
        setTimeout(function ()
        {
            form.render(null, filter);
        }, 50);
    });
    //表单提交事件
    dom.on('submit', ELEM, submit)
        .on('click', '*[lay-submit]', submit);
    exports(MOD_NAME, form);
});

 
