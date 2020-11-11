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