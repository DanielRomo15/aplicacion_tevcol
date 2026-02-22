document.addEventListener("DOMContentLoaded", () => {
  const canvas = document.getElementById("chartEstados");
  if (!canvas) return;

  const e0 = Number(canvas.dataset.e0 || 0);
  const e1 = Number(canvas.dataset.e1 || 0);
  const e2 = Number(canvas.dataset.e2 || 0);

  new Chart(canvas, {
    type: "bar",
    data: {
      labels: ["Estado 0", "Estado 1", "Estado 2"],
      datasets: [{ label: "Cantidad", data: [e0, e1, e2] }]
    },
    options: {
      responsive: true,
      plugins: { legend: { display: false } },
      scales: { y: { beginAtZero: true } }
    }
  });
});