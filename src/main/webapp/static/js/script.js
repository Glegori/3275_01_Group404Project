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
function openModifyForm(expenseUser, expenseName, expenseCost, expenseDate, expenseType, expenseStatus){
    document.getElementById("modifyForm").style.width = "38%";
    document.getElementById("modifyForm").style.display = "block";
    console.log(expenseUser, expenseName, expenseCost, expenseDate, expenseType, expenseStatus);
    $(document).ready(function() {
        $('#modifyForm').load("/changeStatus", {expense:expense});
    });
}