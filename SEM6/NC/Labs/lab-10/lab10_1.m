n = 4
firstSystemMatrix = 5 * eye(n) - diag(ones(n-1, 1), -1) - diag(ones(n-1, 1), 1)
firstSystemResults = 3 * ones(n, 1) + triu(ones(n, 1)) + tril(ones(n, 1), 1-n)

disp("Gaussian elimination");
g1 = gaussian_elim(firstSystemMatrix, firstSystemResults)

disp("LUP factorization");
l1 = lup(firstSystemMatrix, firstSystemResults)

disp("Cholesky factorization");
c1 = cholesky(firstSystemMatrix, firstSystemResults)

disp("QR factorization");
q1 = QR(firstSystemMatrix, firstSystemResults)