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
function openModifyForm(id, expenseUser, expenseName, expenseCost,expenseDate, expenseType, expenseStatus, billImage){
    document.getElementById("modifyForm").style.width = "45%";
    document.getElementById("modifyForm").style.display = "block";
    console.log(expenseDate);

    $(document).ready(function() {
        $('#id').val(id);
        $('#expenseUser').val(expenseUser);
        $('#labelExpenseUser').html('<p class="col-md-12 control-label" id="labelExpenseUser">Expense Submitter: </p>');
        $('#labelExpenseUser').append(expenseUser);
        $('#expenseName').val(expenseName);
        $('#labelExpenseName').html('<p class="col-md-12 control-label" id="labelExpenseName">Expense Name: </p>');
        $('#labelExpenseName').append(expenseName);
        $('#expenseCost').val(expenseCost);
        $('#labelExpenseCost').html('<p class="col-md-12 control-label" id="labelExpenseCost">Expense Cost: </p>');
        $('#labelExpenseCost').append(expenseCost);
        $('#expenseDate').val(expenseDate);
        $('#labelExpenseDate').html('<p class="col-md-12 control-label" id="labelExpenseDate">Date of Submission: </p>');
        $('#labelExpenseDate').append(expenseDate);
        $('#expenseType').val(expenseType);
        $('#labelExpenseType').html('<p class="col-md-12 control-label" id="labelExpenseType">Expense Type: </p>');
        $('#labelExpenseType').append(expenseType);
        $('#billImage').val(billImage);
        $('#expenseStatus').val(expenseStatus);
    });
}
function closeSide(){
    document.getElementById("modifyForm").style.width = "0%";
    document.getElementById("modifyForm").style.display = "none";
}