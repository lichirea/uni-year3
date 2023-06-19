n = 7

secondSystemMatrix = 5 * eye(n) - diag(ones(n-1, 1), 1) - diag(ones(n-1, 1), -1) - diag(ones(n-3, 1), 3) - diag(ones(n-3, 1), -3)
secondSystemResults = ones(n, 1) + triu(ones(n, 1))+ tril(ones(n, 1), 1-n) + triu(ones(n, 1), -2) + tril(ones(n, 1), 3-n)

[j2, nit2J] = jacobi(secondSystemMatrix, secondSystemResults, zeros(size(secondSystemResults)), 0.0001, 30);
[g2, nit2G] = gauss_seidel(secondSystemMatrix, secondSystemResults, zeros(size(secondSystemResults)), 0.0001, 30);
[s2, nit2S] = sor(secondSystemMatrix, secondSystemResults, zeros(size(secondSystemResults)), 0.0001, 30);

disp("Jacobi method");
disp(j2);
disp("Number of iterations");
disp(nit2J);
disp("Gauss Seidel method");
disp(g2);
disp("Number of iterations");
disp(nit2G);
disp("SOR method");
disp(s2);
disp("Number of iterations");
disp(nit2S);