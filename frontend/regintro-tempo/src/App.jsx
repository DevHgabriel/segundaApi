
import './App.css'
import { useState,useEffect } from 'react'; 


const API = "http://localhost:8080/tempos";
function App() {
const [registros, setRegistros] = useState([]);
  const [tempo,setTempo] = useState("");
  const [valor,setValor] = useState("");

  function carregar(){
    fetch(API)
    .then((resposta) => resposta.json())
    .then((dados) => setRegistros(dados))
  }
  useEffect(() => {
    carregar();
  },[]);

  function cria(evento){
    evento.preventDefault();
    fetch(API, {
      method: "POST",
      headers:{"Content-Type": "application/json"},
      body: JSON.stringify({
        tempo: new Date(tempo).toDateString(),
        Valor: Number(valor),
      }),
    }).then(() => {
      setTempo("");
      setValor("");
      carregar();
    });
  }

  function excluir(id){
    fetch(`${API}/${id}`, {method:"DELETE"}).then(() => carregar());
  }

   function estaVencido(registro) {
    return new Date(registro.tempo) < new Date();
  }
  return (
    <div>
        <h1>Registro de Tempo</h1>

      <form onSubmit={cria}>
        <input
          type="datetime-local"
          value={tempo}
          onChange={(e) => setTempo(e.target.value)}
          required
        />
        <input
          type="number"
          step="0.01"
          value={valor}
          onChange={(e) => setValor(e.target.value)}
          placeholder="Valor"
          required
        />
        <button type="submit">Registrar</button>
      </form>

      <ul>
        {registros.map((registro) => (
          <li key={registro.id}>
            R$ {registro.valor} | até{" "}
            {new Date(registro.tempo).toLocaleString("pt-BR")} |{" "}
            {estaVencido(registro) ? "❌ Inválido (vencido)" : "✅ Válido"}
            <button onClick={() => excluir(registro.id)}>Excluir</button>
          </li>
        ))}
      </ul>
    </div>
  )
}

export default App
