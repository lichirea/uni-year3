function [x, nit] = SOR(A, b, x0, err, maxnit)
    n = length(b);
    x = x0;
    nit = 0;
    error = inf;
    omega = 1.2; 
    
    while error > err && nit < maxnit
        x_old = x;
        
        for i = 1:n
            x(i) = omega * (b(i) - A(i, [1:i-1, i+1:n]) * x([1:i-1, i+1:n])) / A(i, i) + (1 - omega) * x(i);
        end
        
        nit = nit + 1;
        error = norm(x - x_old, inf);
    end
end
