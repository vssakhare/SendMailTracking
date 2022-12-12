/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


  $('document').ready(function(){
                $('#selectUnsubscribeReason').change(function() {
                  if ( $(this).val() == "others") {
                      $('#unsubscribereason').val("").fadeIn();
                  }else{
                       $('#unsubscribereason').val("").fadeOut();     
                      
                  }
                   $(".errorMsg").hide();                 
                });                
                 $('#unsubscribereason').keypress(function() {
                     if ( $(this).val() != "") { 
                          $(".errorMsg").hide();
                     }    
                 });
                 
 $('#subscribeform').on('click', function(){

   var reason =$.trim( $("#selectUnsubscribeReason").val());
   var email =$.trim( $("#txtMail").val());
   var reason_others =$.trim( $("#unsubscribereason").val());  
    if ( email =="" ){
    $(".mailerrorMsg").css('display','block');
    return false;
    }
    else if(email !==""){
     $(".mailerrorMsg").css('display','none');    
    }
    if ( reason =="" ){
    $(".errorMsg").css('display','block');
    return false;
    }else if(reason_others =="" && reason == "others") { 
    $(".errorMsg").css('display','block');
    return false;
   }
   

 });
            });//End of doc ready
            
            function ValidateEmail(txtemail)
{
   
var mailformat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
if(txtemail.value.match(mailformat))
{

document.form1.txtMail.focus();
return true;
}
else
{
alert("You have entered an invalid email address!");
document.form1.txtMail.focus();
return false;
}
}

function Unsubcribe(txtemail)
{
     if (ValidateEmail(txtemail)) {
      if($.trim( $("#selectUnsubscribeReason").val())=="others")
        {
    var UnsubscribeReason = $.trim( $("#unsubscribereason").val());
        }
    else
    {
        var UnsubscribeReason = $.trim( $("#selectUnsubscribeReason").val());
    }
     var email =$.trim( $("#txtMail").val());
     }
      var uiActionName ="unsubscribe_message";
     var url = "erp";
     
     
        var params = "uiActionName=" + encodeURIComponent(uiActionName)
              + "&email=" + email
    + "&UnsubscribeReason=" + UnsubscribeReason   
                ;
 postForm(url, params, "get");
}

function postForm(action, params, method) {
    method = method || "post"; // Set method to post by default if not specified.

    var hiddenForm = document.createElement("form");
    hiddenForm.setAttribute("method", method);
    hiddenForm.setAttribute("action", action);
    hiddenForm.setAttribute("id", "hiddenForm");
    hiddenForm.setAttribute("name", "hiddenForm");

    while (params.indexOf("=") > -1) {
        var par = "";
        if (params.indexOf("&") > -1) {
            par = params.substring(0, params.indexOf("&"));
            params = params.substring(params.indexOf("&") + 1, params.length);
        } else {
            par = params;
            params = "";
        }

        var name = par.substring(0, par.indexOf("="));
        var value = decodeURIComponent(par.substring(par.indexOf("=") + 1, par.length));
        var formElement = document.createElement("input");
        formElement.setAttribute("type", "hidden");
        formElement.value = value;
        formElement.name = name;
        formElement.id = name;
        hiddenForm.appendChild(formElement);
    }   
    
    document.body.appendChild(hiddenForm);
    hiddenForm.submit();
}