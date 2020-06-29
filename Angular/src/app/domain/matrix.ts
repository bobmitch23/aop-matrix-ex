export class Matrix {
    private values: number[][]

	constructor() {
		this.values = new Array<Array<number>>();
    }
    
    public add_row(row: number[]): void{
        this.values.push(row)
    }

    public getValues(): number[][]{
        return this.values
    }

    public setValues(values: number[][]): void{
        this.values = values
    }
}
