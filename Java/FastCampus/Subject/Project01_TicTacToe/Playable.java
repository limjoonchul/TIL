package com.company.Tictacto;





public interface Playable<T> {
    boolean play(T move,Player player);
}
