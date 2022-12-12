/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function NoOfDays(txtFrmDt, txtToDt)
{
    // alert("In NoOfDays Function" + frmdt + todt);

    var frmdt1 = txtFrmDt.replace('-', ' ');
    var frmdt = frmdt1.replace('-', ' 20');

    var todt1 = txtToDt.replace('-', ' ');
    var todt = todt1.replace('-', ' 20');
    var d1 = new Date(frmdt);
    var d2 = new Date(todt);
    // alert("SrtToDt " + d1 + " " + d2);
    var nod = Math.round((d2 - d1) / (1000 * 60 * 60 * 24));
    nod = nod + 1;

    //  alert("NOD 1:- "+ nod);
    return  nod;
}

function getYYYYMM(txtToDt)
{
    var todt1 = txtToDt.replace('-', ' ');
    var todt = todt1.replace('-', ' 20');
    var dt = new Date(todt);
    var dtstr = dt.getFullYear().toString();
    dtstr = dtstr + (((dt.getMonth() + 1) > 9 ? '' : '0')).toString() + (dt.getMonth() + 1).toString();
    return dtstr;
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

function getLTCBlockYear(txtToDt) {
    var todt1 = txtToDt.replace('-', ' ');
    var todt = todt1.replace('-', ' 20');
    var dt = new Date(todt);
    var dtstr = Number(dt.getFullYear());
    dtstr = Math.round(dtstr / 4) * 4 + 1;
    dtstr = dtstr.toString();
    dtstr = dtstr + (Number(dtstr.replace('20', '')) + 3).toString();
    return dtstr;
}

function deleteApp(AppId) {
    var uiActionName = document.getElementById("deleteAction").value;
    var action = "delete";
    var url = "erp";
    var params = "uiActionName=" + encodeURIComponent(uiActionName)
            + "&AppId=" + encodeURIComponent(AppId)
            + "&subAction=" + encodeURIComponent(action)
            ;

    postForm(url, params, "get");
}

function viewApp(AppId, viewAction) {debugger;
    var uiActionName = "";
    if (viewAction !== null && viewAction !== undefined) {
        uiActionName = document.getElementById(viewAction).value;
    } else {
        uiActionName = document.getElementById("viewAction").value;
    }
    var action = "view";
    var url = "erp";
    var params = "uiActionName=" + encodeURIComponent(uiActionName)
            + "&AppId=" + encodeURIComponent(AppId)
            + "&subAction=" + encodeURIComponent(action)
            ;

    postForm(url, params, "get");
}

function deleteAppAjax(AppId) {
    var out = {
        response: function validation(info) {
            var jsonObj = JSON.parse(info);
            var de = jsonObj.AppId;
            window.alert("Application Deleted Successfully !");
        }
    };
    var action = "delete";
    var url = "ajax";
    var uiactionName = document.getElementById("deleteAction").value;
    var params = "uiaction=" + encodeURIComponent(uiactionName)
            + "&AppId=" + encodeURIComponent(AppId)
            + "&subAction=" + encodeURIComponent(action)
            ;

    callAjax("POST", url, params, false, out.response);

    var uiActionName = document.getElementById("deleteRedirect").value;
    var action = "delete";
    var url = "erp";
    var params = "uiActionName=" + encodeURIComponent(uiActionName)
            + "&subAction=" + encodeURIComponent(action)
            ;

    postForm(url, params, "get");

}
function viewEmpAppVerified(appl_id,VendorInvNo, EmpNo, viewAction,PoNumber,module_type) { 
    var uiActionName = "";
    if (viewAction !== null && viewAction !== undefined) {
        uiActionName = document.getElementById(viewAction).value;
    } else {
      uiActionName = document.getElementById("viewAction").value;
    }
    var action = "view";
    var url = "erp";
    var params = "uiActionName=" + encodeURIComponent(uiActionName)
             + "&appl_id=" + encodeURIComponent(appl_id)
            + "&VendorInvNo=" + encodeURIComponent(VendorInvNo)
            + "&EmpNo=" + encodeURIComponent(EmpNo)
            + "&PoNumber=" + encodeURIComponent(PoNumber)
            + "&subAction=" + encodeURIComponent(action)
            + "&module_type=" + encodeURIComponent(module_type)
            ;

    postForm(url, params, "get");
}
function viewEmpAppVerified_PS(appl_id,VendorInvNo, EmpNo, viewAction,PoNumber,module_type) { 
    var uiActionName = "";
    if (viewAction !== null && viewAction !== undefined) {
        uiActionName = document.getElementById(viewAction).value;
    } else {
      uiActionName = document.getElementById("viewAction").value;
    }
    var action = "view";
    var url = "erp";
    var params = "uiActionName=" + encodeURIComponent(uiActionName)
             + "&appl_id=" + encodeURIComponent(appl_id)
            + "&VendorInvNo=" + encodeURIComponent(VendorInvNo)
            + "&EmpNo=" + encodeURIComponent(EmpNo)
            + "&PoNumber=" + encodeURIComponent(PoNumber)
            + "&subAction=" + encodeURIComponent(action)
            + "&module_type=" + encodeURIComponent(module_type)
            ;

    postForm(url, params, "get");
}
function viewEmpApp(SesMigoNum,VendorInvNo, EmpNo, viewAction,PoNumber,module_type) { 
    var uiActionName = "";
    if (viewAction !== null && viewAction !== undefined) {
        uiActionName = document.getElementById(viewAction).value;
    } else {
      uiActionName = document.getElementById("viewAction").value;
    }
    var action = "view";
    var url = "erp";
    var params = "uiActionName=" + encodeURIComponent(uiActionName)
             + "&SesMigoNum=" + encodeURIComponent(SesMigoNum)
            + "&VendorInvNo=" + encodeURIComponent(VendorInvNo)
            + "&EmpNo=" + encodeURIComponent(EmpNo)
            + "&PoNumber=" + encodeURIComponent(PoNumber)
            + "&subAction=" + encodeURIComponent(action)
            + "&module_type=" + encodeURIComponent(module_type)
            ;

    postForm(url, params, "get");
}
function viewvendorApp_ps(VendorInvNo, EmpNo, viewAction,MsedclInvNumber,module_type) { 
    var uiActionName = "";
    if (viewAction !== null && viewAction !== undefined) {
        uiActionName = document.getElementById(viewAction).value;
    } else {
      uiActionName = document.getElementById("viewAction").value;
    }
    var action = "view";
    var url = "erp";
    var params = "uiActionName=" + encodeURIComponent(uiActionName)
             + "&VendorInvNo=" + encodeURIComponent(VendorInvNo)
            + "&EmpNo=" + encodeURIComponent(EmpNo)
            + "&MsedclInvNumber=" + encodeURIComponent(MsedclInvNumber)
            + "&subAction=" + encodeURIComponent(action)
            + "&module_type=" + encodeURIComponent(module_type)
            ;

    postForm(url, params, "get");
}
function viewEmpApp_ps(VendorInvNo, EmpNo, viewAction,MsedclInvNumber,module_type) { 
    var uiActionName = "";
    if (viewAction !== null && viewAction !== undefined) {
        uiActionName = document.getElementById(viewAction).value;
    } else {
      uiActionName = document.getElementById("viewAction").value;
    }
    var action = "view";
    var url = "erp";
    var params = "uiActionName=" + encodeURIComponent(uiActionName)
             + "&VendorInvNo=" + encodeURIComponent(VendorInvNo)
            + "&EmpNo=" + encodeURIComponent(EmpNo)
            + "&MsedclInvNumber=" + encodeURIComponent(MsedclInvNumber)
            + "&subAction=" + encodeURIComponent(action)
            + "&module_type=" + encodeURIComponent(module_type)
            ;

    postForm(url, params, "get");
}
function viewEmpApp1(AppId, EmpNo, viewAction,poNumber,module_type) { //to differentiate between proj id and proj system
    var uiActionName = "";
    if (viewAction !== null && viewAction !== undefined) {
        uiActionName = document.getElementById(viewAction).value;
    } else {
        uiActionName = document.getElementById("viewAction").value;
    }
    var action = "view";
    var url = "erp";
    var params = "uiActionName=" + encodeURIComponent(uiActionName)
            + "&AppId=" + encodeURIComponent(AppId)
            + "&EmpNo=" + encodeURIComponent(EmpNo)
            + "&poNumber=" + encodeURIComponent(poNumber)
            + "&subAction=" + encodeURIComponent(action)
            + "&module_type=" + encodeURIComponent(module_type)
            ;

    postForm(url, params, "get");
}


function deleteJRepAppAjax(report_id) {
    debugger;

    

    var out = {
        response: function validation(info) {
            var jsonObj = JSON.parse(info);
            var id = jsonObj.AppId;
             
            window.alert("Data Deleted  Successfully !");
            location.reload();
        }
    };
    var url = "ajax";
    var uiactionName = "deleteJoinForm";
    var params = "uiaction=" + uiactionName             
            + "&report_id=" + encodeURIComponent(report_id)
          
            ;


    callAjax("POST", url, params, false, out.response);
}

function viewEmpInv(POType,PoNumber, sesmigoinvno, EmpNo, viewAction) { 
    var uiActionName = "";
    if (viewAction !== null && viewAction !== undefined) {
        uiActionName = document.getElementById(viewAction).value;
    } else {
        uiActionName = document.getElementById("viewAction").value;
    }
    var action = "view";
    var url = "erp";
    var params = "uiActionName=" + encodeURIComponent(uiActionName)
            + "&POType=" + encodeURIComponent(POType)
            + "&PoNumber=" + encodeURIComponent(PoNumber)
            + "&sesmigoinvno=" + encodeURIComponent(sesmigoinvno)
            + "&EmpNo=" + encodeURIComponent(EmpNo)
            + "&subAction=" + encodeURIComponent(action)
    
            ;

    postForm(url, params, "get");
}


 