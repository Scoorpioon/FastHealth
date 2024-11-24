import { useRef } from 'react';
import '../Styles/Popup.scss';

const Popup = (props) => {
    const popup = useRef(null);

    return(
        <div id="popup" ref={popup}>
            <div className="fundoFalso" onClick={() => props.atualizacao([false, popup])}>
            </div>

            <dialog open>
                <button className="fechar" onClick={() => props.atualizacao([false, popup])}>X</button>
                <div className="conteudo">
                    <h2>Tem certeza?</h2>
                    <span>O paciente <strong>{props.nome}</strong> será removido da fila de espera.</span>
                </div>
                <div className="botoes">
                    <button onClick={() => props.atualizacao([false, popup])}>Cancelar</button>
                    <button onClick={() => props.atualizacao([true, popup])}>Remover paciente</button>
                </div>
            </dialog>
        </div>
    );
};

export default Popup;