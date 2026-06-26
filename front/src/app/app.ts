import { Component, signal } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';
import { OperacionService } from './core/operaciones.service';
import { CommonModule } from '@angular/common';
import { Operacion } from './core/operacion.model';

@Component({
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  selector: 'app-root',
  templateUrl: './app.html',
  styleUrls: ['./app.css'] // ojo: es styleUrls (plural)
})
export class App {
  operaciones = signal<Operacion[]>([]);
  montoDestino = signal<number>(0);

  form!: FormGroup; 

  constructor(private fb: FormBuilder, private service: OperacionService) {}

  ngOnInit() {
    // inicializas el form dentro de ngOnInit o en el constructor
    this.form = this.fb.group({
      monedaOrigen: ['', Validators.required],
      monedaDestino: ['', Validators.required],
      montoOrigen: [0, [Validators.required, Validators.min(0.01)]],
      tipoCambio: [0, [Validators.required, Validators.min(0.000001)]],
      estado: ['PENDIENTE', Validators.required]
    });

    // cálculo dinámico
    this.form.valueChanges.subscribe(v => {
      if (v.montoOrigen && v.tipoCambio) {
        this.montoDestino.set(v.montoOrigen * v.tipoCambio);
      }
    });

    this.cargarOperaciones();
  }

  cargarOperaciones() {
    this.service.listar().subscribe(data => this.operaciones.set(data));
  }

  registrarOperacion() {
    if (this.form.invalid) return;

    const op: Operacion = this.form.value as Operacion;
    this.service.registrar(op).subscribe(nueva => {
      this.operaciones.update(arr => [...arr, nueva]);
      this.form.reset({ estado: 'PENDIENTE' });
    });
  }

  actualizarEstado(id: number, nuevoEstado: string) {
    this.service.actualizarEstado(id, nuevoEstado).subscribe(actualizada => {
      this.operaciones.update(arr =>
        arr.map(o => o.id === actualizada.id ? actualizada : o)
      );
    });
  }
}
