entity XNOR_test is port(
    a, b, x, y: in bit;
    F, Z: out bit
);
end XNOR_test;  

architecture XNOR_test_arch of XNOR_test is
begin
    process(a, b, x, y)
    begin
        F <= x xnor y;
        Z <= (x and y) xnor (a xnor b);
    end process;
end XNOR_test_arch;

