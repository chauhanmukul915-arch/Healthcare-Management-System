document.addEventListener("DOMContentLoaded", () => {
  document.querySelectorAll("[data-toggle]").forEach(button => {
    const target = document.getElementById(button.dataset.toggle);
    if (!target) return;
    target.classList.add("collapsible");
    target.hidden = true;
    button.addEventListener("click", () => {
      target.hidden = !target.hidden;
      if (!target.hidden) target.scrollIntoView({ behavior: "smooth", block: "start" });
    });
  });

  document.querySelectorAll("[data-table-search]").forEach(input => {
    input.addEventListener("input", () => {
      const table = document.getElementById(input.dataset.tableSearch);
      if (!table) return;
      const query = input.value.toLowerCase().trim();
      table.querySelectorAll("tbody tr").forEach(row => {
        row.hidden = !row.textContent.toLowerCase().includes(query);
      });
    });
  });

  document.querySelectorAll("form").forEach(form => {
    form.addEventListener("submit", event => {
      if (!form.checkValidity()) {
        event.preventDefault();
        form.reportValidity();
      }
    });
  });
});
