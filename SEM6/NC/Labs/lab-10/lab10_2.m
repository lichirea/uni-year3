n = 7
secondSystemMatrix = 5 * eye(n) - diag(ones(n-1, 1), 1) - diag(ones(n-1, 1), -1) - diag(ones(n-3, 1), 3) - diag(ones(n-3, 1), -3)
secondSystemResults = ones(n, 1) + triu(ones(n, 1))+ tril(ones(n, 1), 1-n) + triu(ones(n, 1), -2) + tril(ones(n, 1), 3-n)

disp("Gaussian elimination");
g2 = gaussian_elim(secondSystemMatrix, secondSystemResults)

disp("LUP factorization");
l2 = lup(secondSystemMatrix, secondSystemResults)

disp("Cholesky factorization");
c2 = cholesky(secondSystemMatrix, secondSystemResults)

disp("QR factorization");
q2 = QR(secondSystemMatrix, secondSystemResults)