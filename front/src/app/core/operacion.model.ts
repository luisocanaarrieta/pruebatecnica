export interface Operacion {
  id?: number;
  monedaOrigen: string;
  monedaDestino: string;
  montoOrigen: number;
  tipoCambio: number;
  montoDestino: number;
  fechaOperacion: string; // o Date si lo parseas
  estado: string;
}
