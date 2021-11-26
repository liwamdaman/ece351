ENTITY OR_gate is port (
    x,y : in bit;
    z: out bit
);
end OR_gate;

architecture OR_gate_arch of OR_gate is begin
    process(x,y)
    begin
        if((x='0') and (y='0')) then
            z <= '0';
        else
            z <= '1';
        end if;
    end process;
end OR_gate_arch;
