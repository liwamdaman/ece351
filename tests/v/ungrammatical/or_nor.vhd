entity OR_NOR is port (
    x: in std_logic;
    y: in std_logic;
    F1: out std_logic;
    F2: out std_logic
);
end OR_NOR;

architecture OR_NOR_arch of OR_NOR is
begin  
    process(x,y)
    begin
        if((x='0') and (y='0')) then
            F1 <= '0';
        else
            F1 <= '1';
        end if;
    end process;
    
    process(x, y)
    begin
        if (x='0' and y='0') then
            F2 <= '1';
        else
            F2 <= '0';
        end if;
    end process;
    
end OR_NOR_arch;
