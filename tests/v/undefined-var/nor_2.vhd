entity NOR_gate is port(
    x, y: in bit;
    F: out bit
);
end NOR_gate;  

architecture NOR_gate_arch of NOR_gate is
begin
    process(x, z)
    begin
        if (x='0' and y='0') then
            F <= '1';
        else
            F <= '0';
        end if;
    end process;

end NOR_gate_arch;

