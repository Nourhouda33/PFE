import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Tache } from '../Entity/Tache.Entity';
import { CrudService } from '../Service/crud.service';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {
  todoForm: FormGroup;
  tasks: Tache[] = [];
  inprogress: Tache[] = [];
  done: Tache[] = [];
  updateIndex: number | undefined;
  isEditEnabled: boolean = false;

  constructor(private fb: FormBuilder, private todoService: CrudService) { }

  ngOnInit(): void {
    this.todoForm = this.fb.group({
      item: ['', Validators.required]
    });
    this.loadTasks();
  }

  loadTasks() {
    this.todoService.getTasks().subscribe((tasks: Tache[]) => this.tasks = tasks);
  }

  addTask() {
    const discription = this.todoForm.value.item;
    this.todoService.addTask({ discription, done: false }).subscribe(() => {
      this.loadTasks();
      this.todoForm.reset();
    });
  }
  onEdit(item: Tache, index: number) {
    this.todoForm.controls['item'].setValue(item.discription);
    this.updateIndex = index;
    this.isEditEnabled = true;
  }

  deleteTask(id: number) {
    this.todoService.deleteTask(id).subscribe(() => {
      this.loadTasks();
    });
  }

  deleteinprogress(id: number) {
    this.todoService.deleteTask(id).subscribe(() => {
      this.loadTasks();
    });
  }

  deletedone(id: number) {
    this.todoService.deleteTask(id).subscribe(() => {
      this.loadTasks();
    });
  }

  
  updateTask() {
    if (this.updateIndex !== undefined) {
      const discription = this.todoForm.value.item;
      const id = this.tasks[this.updateIndex].id;
      this.todoService.updateTask(id, { discription, done: false }).subscribe(() => {
        this.loadTasks();
        this.todoForm.reset();
        this.updateIndex = undefined;
        this.isEditEnabled = false;
      });
    }
  }

  drop(event: CdkDragDrop<Tache[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      );
    }
  }
}
