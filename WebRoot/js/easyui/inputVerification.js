/**
 * auto TRL
 * time 2014/11/10.
 * validatebox验证扩展
*/
/*
+-----------------------+-------------------------------------------------------+
| 		类型(datatype)	|		描	述											|
+-----------------------+-------------------------------------------------------+
|int					|只能输入正整数										    	|
+-----------------------+-------------------------------------------------------+
|plusFloat				|正浮点数 必须是整 可以只输入整数部分						        |
+-----------------------+-------------------------------------------------------+
|isIp    				|IP判断						                            |
+-----------------------+-------------------------------------------------------+
|ZH_CH    				|包含中文的长度判断				                            |
+-----------------------+-------------------------------------------------------+
|uZH_CH    				|不能含有中文			                                    |
+-----------------------+-------------------------------------------------------+
|Email  				|邮箱判断     		                                        |
+-----------------------+-------------------------------------------------------+
|post  				    |邮政编码判断  		                             	        |
+-----------------------+-------------------------------------------------------+
|phone  				|手机号码进行判断  		                                    |
+-----------------------+-------------------------------------------------------+
|land  				    |座机进行判断		                                        |
+-----------------------+-------------------------------------------------------+
|phoneAndLand  		    |座机或者手机号码进行判断		                                |
+-----------------------+-------------------------------------------------------+
validType:'length[3]'
**/

/***
 * 输入框校验扩展
 */

$.extend($.fn.validatebox.defaults.rules, {
    /**
     * 判断为正整数
     */
    Int: {
        validator: function(value,param){
            return intFunc(value)&&(value.length <= param[0]);
        },
        message: '请输入正整数不能超过{0}位'
    },
    /**
     * 长度判断
     */
    Lenght:{
    	validator: function(value,param){
            return value.length < param[0];
        },
        message: '长度不能超过{0}位'
    },
    surpass:{
    	validator: function(value,param){
            return value.length <= param[0];
        },
        message: '长度不能超过{0}'
    },
    /**
     * 长度必须等于
     */
    BeEqualToInt:{
      validator: function(value,param){
	        return intFunc(value)&&(value.length == param[0]);
	    },
	    message: '只能输入长度为{0}位的整数'
    },
    /**
     * 正浮点数判断
     */
    plusFloat:{
        validator: function(value,param){
            if(!floatFunc(value)){
                //如果不是小数必须为整数并且长度不能超过限定的长度
                return IntegerFunc(value)&&value.length<=param[0]-param[1];
            }else{
                if(floatFunc(value)){
                    var array = value.split('.');
                    return IntegerFunc(array[0])&&(array[0].length<=param[0]-param[1])&&(array[1].length<=param[1]);
                }else{
                    return false;
                }
            }
        },
        message: '请输入数字有效数位(包括小数)不能超过{0}位,其中小数位不能超过{1}位'
    },
    /**
     * IP判断
     */
    isIp:{
        validator: function(value,param){
            return IPFunc(value);
        },
        message: '请输入正确的Ip地址'
    },
    /**
     * 字符串长度判断包含中文长度判断
     */
    ZH_CH:{
        validator: function(value,param){
            return getStrLength(value)<=param[0];
        },
        message: '长度不成超过{0}字符,中文占两个字符'
    },
    /**
     *字符串不能保护中文
     */
    uZH_CH:{
        validator: function(value){
            return getStrLength(value)<=value.length;
        },
        message: '不能有中文'
    },
    /**
     * 邮箱判断
     */
    Email:{
        validator: function(value){
            return checkEmail(value);
        },
        message: '请输入正确的邮箱地址'
    },
    /**
     * 传真判断
     */
    CZ:{
        validator: function(value){
            return checkCZ(value);
        },
        message: '请输入正确的传真地址'
    },
    post:{
        validator: function(value){
            return checkPost(value);
        },
        message: '请输入正确的邮政编码'
    },
    phone:{
        validator: function(value){
            return phone(value);
        },
        message: '手机号码只能以13,14,15,17,18开头,且手机号码为11位数字(如：13999999999)'
    },
    land:{
        validator: function(value){
            return land(value);
        },
        message: '固定电话前请加区号,且区号-后面为7或8位数(如：0731-86868686)'
    },
    phoneAndLand:{
        validator: function(value){
            return land(value)|| phone(value);
        },
        message: '联系电话格式不正确!'
    }
} );
//判断正整数
var  intFunc = function(val)
{
     return  /^[1-9]+[0-9]*]*$/.test(val);
};

//判断是否是整数
var IntegerFunc=function(val){
	return  /^[0-9]+([.]{0,1}[0-9]+){0,1}$/.test(val);
};

/***
 *判断是否为小数
 *可以是整数也可以是负数
 *成功返回true 失败返回false
 * */
var floatFunc = function(val){
        var r= /^[+-]?[1-9]?[0-9]*\.[0-9]*$/;
        return r.test(val);
};

/**
 *用途：检查输入字符串是否IP地址
 *如果通过验证返回true,否则返回false
 */
var IPFunc = function( s )
{
    var reg = /^\d{1,3}(\.\d{1,3}){3}$/;
    if(reg.exec(s))
    {
        var ary = s.split(".");
        for(var key=0;key<ary.length;key++)
        {
            if(parseInt(ary[key]) > 255)
            {
                return false;
            }
        }
        return true;
    }else{
        return false;
    }
};


/**
 * 得到字符串的长度对中文进行处理
 * 中文长度为2
 */
var getStrLength = function(str)
{
    var l = str.length;
    var n = l;
    var add=false;
    for ( var i=0; i<l; i++ )
    {
        if ( str.charCodeAt(i) <0 || str.charCodeAt(i) >255 ||str.charCodeAt(i)==10 ||str.charCodeAt(i)==13 )
            n=n+1;
        if ( str.charCodeAt(i)==10 ||str.charCodeAt(i)==13 )
            add=true;
    }
    if ( add ) n=n+2;

    return n;
};

/**
 *邮箱判断
 * 成功返回true 失败返回false
 */
var checkEmail =function (strEmail) {
    return (strEmail.indexOf("@")>0 && strEmail.indexOf(".")>strEmail.indexOf("@")>0);
};

/**
 *邮政编码判断
 */
var checkPost = function(o)
{
    var temp="0123456789";
    var tocheck = o;

    if (tocheck.length != 6)
    {
        return false;
    }
    for (var j=0;j<tocheck.length;j++)
    {
        if (temp.indexOf(tocheck.substring(j,j+1))<0)
        {
            return false;
        }
    }
    return true;
};

/***
 * 手机号码判断
 */
var phone = function (phone){
    var pat = /^1[34578]\d{9}$/; //手机(13,14,15,18,17)
    if (phone.substr(0, 2) == "13" || phone.substr(0, 2) == "14" || phone.substr(0, 2) == "15" || phone.substr(0, 2) == "18"|| phone.substr(0, 2) == "17") {
        if (phone.length != 11) {
            return false;
        }
        if (pat.test(phone)) {
            return true;
        }
        else {
            return false;
        }
    }else{
        return false;
    }
};

var land = function(phone){
    var patten=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;//座机(0731-88888888)
    var checkphone = phone.toString().split('-');
    if (checkphone.length > 2) {
        return false;
    }else if(checkphone.length ==2){
        if (patten.test(phone)) {
            return true;
        }
        else {
            return false;
        }
    }else{
        return false;
    }
};

//////
var checkCZ = function(cz){
	var patten=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;//座机(0731-88888888)
    var checkphone = cz.toString().split('-');
    if (checkphone.length > 2) {
        return false;
    }else if(checkphone.length ==2){
        if (patten.test(cz)) {
            return true;
        }
        else {
            return false;
        }
    }else{
        return false;
    }
};


