n = 3

firstSystemMatrix = 5 * eye(n) - diag(ones(n-1, 1), -1) - diag(ones(n-1, 1), 1)
firstSystemResults = 3 * ones(n, 1) + triu(ones(n, 1)) + tril(ones(n, 1), 1-n)

[j1, nit1J] = jacobi(firstSystemMatrix, firstSystemResults, zeros(size(firstSystemResults)), 0.0001, 30);
[g1, nit1G] = gauss_seidel(firstSystemMatrix, firstSystemResults, zeros(size(firstSystemResults)), 0.0001, 30);
[s1, nit1S] = sor(firstSystemMatrix, firstSystemResults, zeros(size(firstSystemResults)), 0.0001, 30);

disp("Jacobi method");
disp(j1);
disp("Number of iterations");
disp(nit1J);
disp("Gauss Seidel method");
disp(g1);
disp("Number of iterations");
disp(nit1G);
disp("SOR method");
disp(s1);
disp("Number of iterations");
disp(nit1S);
