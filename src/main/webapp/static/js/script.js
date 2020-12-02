function openMenu() {
  document.getElementById("idSideMenu").style.width = "250px";
}

function closeMenu() {
  document.getElementById("idSideMenu").style.width = "0";
  document.getElementById("expenseForm").style.display = "none";
}

function openExpenseForm(){
document.getElementById("expenseForm").style.width = "38%";
document.getElementById("expenseForm").style.display = "block";
$(document).ready(function(){
   $('#expenseForm').load("/submitExpense");
});
}
function openModifyForm(id, expenseUser, expenseName, expenseCost,expenseDate, expenseType, expenseStatus, billImage,expenseDesc){
    document.getElementById("modifyForm").style.width = "45%";
    document.getElementById("modifyForm").style.display = "block";
//this needs some modification for formatting
    $(document).ready(function() {
        $('#id').val(id);
        $('#expenseUser').val(expenseUser);
        $('#labelExpenseUser').html('<p class="col-md-12 control-label" id="labelExpenseUser"><strong>Expense Submitter: </strong>' + expenseUser + '</p>');
        $('#expenseName').val(expenseName);
        $('#labelExpenseName').html('<p class="col-md-12 control-label" id="labelExpenseName"><strong>Expense Name: </strong>' + expenseName + '</p>');
        $('#expenseCost').val(expenseCost);
        $('#labelExpenseCost').html('<p class="col-md-12 control-label" id="labelExpenseCost"><strong>Expense Cost: </strong>' + expenseCost + '</p>');
        $('#labelDate').html('<p class="col-md-12 control-label" id="labelExpenseDate"><strong>Date of Submission: </strong>' + expenseDate + '</p>');
        $('#expenseDate').val(expenseDate);
        $('#expenseType').val(expenseType);
        $('#labelExpenseType').html('<p class="col-md-12 control-label" id="labelExpenseType"><strong>Expense Type: </strong>' + expenseType + '</p>');
        $('#billImage').val(billImage);
        $('#expenseStatus').val(expenseStatus);
        $('#expenseDesc').val(expenseDesc);
    });
}

function openEditUserForm(id, userName,password, userType, reportsFrom, total){
    document.getElementById("editUserForm").style.width = "45%";
    document.getElementById("editUserForm").style.display = "block";
//this needs some modification for formatting
    $(document).ready(function() {
        $('#id').val(id);
        $('#password').val(password)
        $('#userName').val(userName);
        $('#labelUserName').html('<p class="col-md-12 control-label" id="labelUserName"><strong>User Name: </strong>' + userName + '</p>');
        $('#reportsFrom').val(reportsFrom);
        // $('#labelReportsFrom').html('<p class="col-md-12 control-label" id="labelReportsFrom"><strong>Reports From: </strong>' + reportsFrom + '</p>');
        $('#total').val(total);
        // $('#labelTotal').html('<p class="col-md-12 control-label" id="labelTotal"><strong>Total: </strong>' + total + '</p>');
        $('#userType').val(userType);
    });
}



function closeSide(){
    document.getElementById("modifyForm").style.width = "0%";
    document.getElementById("modifyForm").style.display = "none";

}

function closeUserForm(){
    document.getElementById("editUserForm").style.width = "0%";
    document.getElementById("editUserForm").style.display = "none";
}